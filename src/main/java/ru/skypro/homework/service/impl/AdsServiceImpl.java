package ru.skypro.homework.service.impl;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import ru.skypro.homework.exception.SecurityAccessException;
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
  private SecurityService securityService;


  public AdsServiceImpl(AdsRepository adsRepository, CommentRepository commentRepository,
      UserRepository userRepository, AdMapper adMapper, CommentMapper commentMapper,
      ImageRepository imageRepository, ImageMapper imageMapper, UserService userService,
      UserMapper userMapper, AdsOtherMapper adsOtherMapper, SecurityService securityService) {
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
    this.securityService = securityService;
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
  public void deleteComments(Integer pk, Integer id, Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(pk).orElseThrow(ElemNotFound::new);
    CommentEntity comment = commentRepository.findById(id).orElseThrow(ElemNotFound::new);
    if (securityService.isAdmin(authentication) || Objects.equals(adEntity.getAuthor().getId(),
        comment.getAuthor().getId())) {
      commentRepository.deleteById(comment.getId());
    } else {
      throw new ElemNotFound();
    }
  }

  /**
   * Получить все объявления
   */
  @Override
  public ResponseWrapperAds getAds() {
    log.info(FormLogInfo.getInfo());
    Collection<AdsDTO> adsAll = adMapper.toDTOList(adsRepository.findAll());
    int count = adsAll.size();
    return new ResponseWrapperAds(count, adsAll);
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

    if (createAds == null || multipartFile == null) {
      log.error(FormLogInfo.getException());
      throw new IllegalArgumentException();
    }

    UserDTO userDTO = userService.getUser(authentication);
    int count = adsRepository.findAll().size();
    Path filePath = getPath(imageAdsDir, count);

    String linkToGetImage = getLinkToGetImage(imageAdsDir,count);

    Files.createDirectories(filePath.getParent());
    Files.deleteIfExists(filePath);

    try (InputStream is = multipartFile.getInputStream();
        OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
    ) {
      bis.transferTo(bos);
    }

    AdsDTO adsDTO = new AdsDTO();
    adsDTO.setTitle(createAds.getTitle());
    adsDTO.setPrice(createAds.getPrice());
    adsDTO.setImage(linkToGetImage);

    AdEntity adEntity = adMapper.toEntity(adsDTO);
    adEntity.setAuthor(userMapper.toEntity(userDTO));
    ImageDTO imageDTO = new ImageDTO();
    imageDTO.setImage(linkToGetImage);
    ImageEntity imageEntity = imageMapper.toEntity(imageDTO);
    imageEntity.setAd(adEntity);
    imageEntity.setPath(linkToGetImage);
    imageRepository.save(imageEntity);
    adEntity.setImageEntities(List.of(imageEntity));
    adsRepository.save(adEntity);

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
    Path filePath = getPath(imageAdsDir, adEntity.getId());

    if(!adEntity.getImageEntities().isEmpty()){
      int idAds = adEntity.getImageEntities().get(0).getId();
      imageRepository
          .deleteById(idAds);
      adEntity.getImageEntities().clear();
    }

    String linkToGetImage = "/ads" + imageAdsDir.substring(1) + "/"
        + adEntity.getId();

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
    imageEntity.setPath(linkToGetImage);
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
   * получить аватарку объявления
   *
   * @param id объявления
   * @return байтовое представление картинки
   */
  public byte[] getPhotoById(Integer id) {
    String linkToGetImage = "ads_photo_dir/" + id;
    byte[] bytes;
    try {
      bytes = Files.readAllBytes(Paths.get(linkToGetImage));
    } catch (IOException e) {
      log.info(FormLogInfo.getCatch());
      throw new RuntimeException(e);
    }
    return bytes;
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

  /**
   * Получить комментарий по adPk объявления и id комментария
   *
   * @param adPk
   * @param id
   */
  @Override
  public CommentDTO getComments(int adPk, int id) {
    CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(id, adPk)
        .orElseThrow(ElemNotFound::new);
    return commentMapper.toDTO(commentEntity);
  }

  /**
   * Изменение комментария пользователя
   *
   * @param adPk
   * @param id
   */
  @Override
  public CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO,
      Authentication authentication) {
    CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(id, adPk)
        .orElseThrow(ElemNotFound::new);

    if (!securityService.isCommentUpdateAvailable(authentication, commentEntity.getAuthor().getId(),
        commentDTO.getAuthor())) {
      throw new SecurityAccessException();
    }

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
  public void removeAds(int id, Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
    if (securityService.isAdmin(authentication) || securityService.checkAuthor(id,
        adEntity.getAuthor())) {
      adsRepository.delete(adEntity);
    } else {
      throw new SecurityAccessException();
    }

  }

  /**
   * Получить объявление по id
   *
   * @param id
   */
  @Override
  public FullAds getAdById(int id, Authentication authentication) {
    return adsOtherMapper.toFullAds(adsRepository.findById(id).orElseThrow(ElemNotFound::new));
  }

  /**
   * Обновить объявление по id
   *
   * @param id
   */
  @Override
  public AdsDTO updateAds(int id, CreateAds createAds, Authentication authentication) {
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);

    if (!securityService.isAdsUpdateAvailable(authentication, adEntity.getAuthor().getId())) {
      throw new SecurityAccessException();
    }

    adEntity.setDescription(createAds.getDescription());
    adEntity.setPrice(createAds.getPrice());
    adEntity.setTitle(createAds.getTitle());
    return adMapper.toDTO(adsRepository.save(adEntity));
  }

  //
  private Path getPath(String nameDir, Integer id) {
    return Path.of(nameDir,
        Objects.requireNonNull(String.valueOf(id)));
  }

  private String getLinkToGetImage(String nameDir, Integer id) {
    return "/ads" + nameDir.substring(1) + "/"
        + id;
  }


}
