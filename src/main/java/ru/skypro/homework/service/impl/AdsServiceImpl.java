package ru.skypro.homework.service.impl;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.AdsOtherMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;


/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
@Slf4j
@Transactional
public class AdsServiceImpl implements AdsService {

  private ImageRepository imageRepository;
  private AdsOtherMapper adsOtherMapper;
  private UserMapper userMapper;
  private UserService userService;
  private ImageMapper imageMapper;
  private AdsRepository adsRepository;
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private AdMapper adMapper;

  private CommentMapper commentMapper;
  @Value("${image.ads.dir.path}")
  private String imageAdsDir;


  public AdsServiceImpl(AdsRepository adsRepository, CommentRepository commentRepository,
      UserRepository userRepository, AdMapper adMapper, CommentMapper commentMapper,
      ImageRepository imageRepository, ImageMapper imageMapper, UserService userService,
      UserMapper userMapper, AdsOtherMapper adsOtherMapper) {
    this.adsRepository = adsRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.adMapper = adMapper;
    this.commentMapper = commentMapper;
    this.imageMapper = imageMapper;
    this.userService = userService;
    this.userMapper = userMapper;
    this.adsOtherMapper = adsOtherMapper;
    this.imageRepository = imageRepository;
  }

  /**
   * Получение всех комментариев объявления
   *
   * @param pk
   * @return
   */
  @Override
  public ResponseWrapperComment getAdsComments(Integer pk) {
    Collection<CommentEntity> allAd = commentRepository.findAllById(Collections.singleton(pk));
    Collection<CommentDTO> commentDTOS = commentMapper.toDTOList(allAd);
    int count = commentDTOS.size();
    return new ResponseWrapperComment(count, commentDTOS);
  }

  /**
   * Добавление коментария к объявлению
   *
   * @param pk         id объявления
   * @param commentDTO коммент
   */
  @Override
  public CommentDTO addAdsComments(Integer pk, CommentDTO commentDTO,
      Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    if (commentDTO == null || pk < 1) {
      throw new ElemNotFound();
    }
    AdEntity adEntity = adsRepository.findById(pk).orElseThrow(ElemNotFound::new);
    CommentEntity comment = commentMapper.toEntity(commentDTO);
    comment.setAd(adEntity);

    UserDTO userDTO = userService.getUser(authentication);
    comment.setAuthor(userMapper.toEntity(userDTO));
    comment.setCreatedAt(LocalDateTime.now());

    commentRepository.save(comment);

    return commentMapper.toDTO(comment);
  }

  /**
   * Удалить комментарий по id объявления и id комментария
   *
   * @param pk
   * @param id
   */
  @Override
  public void deleteComments(Integer pk, Integer id) {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(pk).orElseThrow(ElemNotFound::new);
    CommentEntity comment = commentRepository.findById(id).orElseThrow(ElemNotFound::new);
    if (Objects.equals(adEntity.getAuthor().getId(), comment.getAuthor().getId())) {
      commentRepository.deleteById(comment.getId());
    } else {
      throw new ElemNotFound();
    }
  }

  @Override
  public ResponseWrapperAds getAds() {
    log.info(FormLogInfo.getInfo());
    Collection<AdsDTO> adsAll = adMapper.toDTOList(adsRepository.findAll());
    int count = adsAll.size();
    ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds(count, adsAll);
    return responseWrapperAds;
  }

  /**
   * Метод, который создает новое объявление с картинкой
   *
   * @param createAds      неполная инфа про объявление
   * @param multipartFile  само изображение
   * @param authentication инфа про юзера
   * @return дто объявления
   * @throws IOException
   */
  @Override
  public AdsDTO addAds(CreateAds createAds, MultipartFile multipartFile,
      Authentication authentication) throws IOException {
    log.info(FormLogInfo.getInfo());
    if(createAds == null || multipartFile == null){
      log.error(FormLogInfo.getException());
      throw new IllegalArgumentException();
    }

    Path filePath = Path.of(imageAdsDir,
        getFileUniqueName() + "." + getExtension(
            Objects.requireNonNull(multipartFile.getOriginalFilename())));
    Files.createDirectories(filePath.getParent());
    Files.deleteIfExists(filePath);

    try (InputStream is = multipartFile.getInputStream();
        OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
      bis.transferTo(bos);
    } catch (Exception e) {
      log.info("Ошибка сохранения файла");
    }

    UserDTO userDTO = userService.getUser(authentication);

    AdsDTO adsDTO = new AdsDTO();
    adsDTO.setTitle(createAds.getTitle());
    adsDTO.setPrice(createAds.getPrice());
    List<String> listOfImage = new ArrayList<>();
    String content = null;
    try {
      content = Base64.getEncoder().encodeToString(multipartFile.getBytes());
    } catch (IOException e) {
      log.error(FormLogInfo.getCatch());
    }
    listOfImage.add(content);
    adsDTO.setImage(listOfImage);
    AdEntity adEntity = adMapper.toEntity(adsDTO);
    adEntity.setAuthor(userMapper.toEntity(userDTO));
    adsRepository.save(adEntity);

    ImageDTO imageDTO = new ImageDTO();
    imageDTO.setImage(filePath.toString());
    ImageEntity imageEntity = imageMapper.toEntity(imageDTO);
    imageEntity.setAd(adEntity);
    imageRepository.save(imageEntity);

    return adsDTO;
  }

  /**
   * Добавление фото в объявление
   *
   * @param id
   * @param image
   */
  @Override
  public void uploadImage(Integer id, MultipartFile image) throws IOException {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);

    Path filePath = Path.of(imageAdsDir,
        getFileUniqueName() + "." + getExtension(image.getOriginalFilename()));
    Files.createDirectories(filePath.getParent());
    Files.deleteIfExists(filePath);

    try (InputStream is = image.getInputStream();
        OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
    ) {
      bis.transferTo(bos);
    }
    ImageEntity imageEntity = new ImageEntity();
    imageEntity.setAd(adEntity);
    imageEntity.setPath(filePath.toString());
    adEntity.setImageEntities(List.of(imageEntity));

    imageRepository.save(imageEntity);
  }

  /**
   * Получаем только свои объявления
   *
   * @param authentication данные о пользователе
   * @return общий подсчет своих объявлений + объявления
   */
  @Override
  public ResponseWrapperAds getAdsMe(Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    UserDTO userDTO = userService.getUser(authentication);
    Collection<AdsDTO> adsAll = adMapper.toDTOList(adsRepository.findAll());
    Collection<AdsDTO> adsMe = adsAll.stream().
        filter(x -> x.getAuthor().equals(userDTO.getId())).collect(Collectors.toList());
    int count = adsMe.size();
    return new ResponseWrapperAds(count, adsMe);
  }

  /**
   * вспомогательный медот для загрузки фотографий
   *
   * @return расширение файла
   */
  private String getExtension(String fileName) {
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }

  /**
   * вспомогательный медот для получения уникального имени
   *
   * @return
   */

  private String getFileUniqueName() {
    return UUID.randomUUID().toString();
  }

  @Override
  public CommentDTO getComments(int adPk, int id) {
    CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(id, adPk)
        .orElseThrow(ElemNotFound::new);
    return commentMapper.toDTO(commentEntity);
  }

  @Override
  public CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO) {
    CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(id, adPk)
        .orElseThrow(ElemNotFound::new);

    UserEntity author = userRepository.findById(commentDTO.getAuthor())
        .orElseThrow(ElemNotFound::new);
    commentEntity.setAuthor(author);

    commentEntity.setText(commentDTO.getText());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    commentEntity.setCreatedAt(LocalDateTime.parse(commentDTO.getCreatedAt(), formatter));

    return commentMapper.toDTO(commentRepository.save(commentEntity));
  }

  /**
   * Удаление объявления по id
   *
   * @param id
   */
  @Override
  public void removeAds(int id) {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
    adsRepository.delete(adEntity);
  }

  @Override
  public FullAds getAdById(int id) {
    return adsOtherMapper.toFullAds(adsRepository.findById(id).orElseThrow(ElemNotFound::new));
  }

  @Override
  public AdsDTO updateAds(int id, CreateAds createAds) {
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
    adEntity.setDescription(createAds.getDescription());
    adEntity.setPrice(createAds.getPrice());
    adEntity.setTitle(createAds.getTitle());
    return adMapper.toDTO(adsRepository.save(adEntity));
  }


}
