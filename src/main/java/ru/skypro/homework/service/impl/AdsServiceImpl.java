package ru.skypro.homework.service.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.Properties;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.AdsOtherMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import javax.transaction.Transactional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
@Slf4j
@Transactional
public class AdsServiceImpl implements AdsService {

  private final AdsRepository adsRepository;
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private AdMapper adMapper;
  private AdsOtherMapper adsOtherMapper;

  private ImageRepository imageRepository;

  public AdsServiceImpl(AdsRepository adsRepository, CommentRepository commentRepository,
                        UserRepository userRepository, AdMapper adMapper, AdsOtherMapper adsOtherMapper, ImageRepository imageRepository) {
    this.adsRepository = adsRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.adMapper = adMapper;
    this.adsOtherMapper = adsOtherMapper;
    this.imageRepository = imageRepository;
  }

  @Value("${image.ads.dir.path}")
  private String imageDir;

  /**
   * Получение всех комментариев объявления
   *
   * @param pk
   * @return
   */
  @Override
  public Collection<CommentDTO> getAdsComments(Integer pk) {
    return null;
  }

  /**
   * Добавление коментария к объявлению
   *
   * @param pk
   */
  @Override
  public void addAdsComments(Integer pk) {

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
    } else throw new ElemNotFound();
  }

  @Override
  public Collection<AdsDTO> getALLAds() {
    log.info(FormLogInfo.getInfo());
//    return adMapper.toDTOList(adsRepository.findAll());
    return Collections.EMPTY_LIST;
  }

  @Override
  public AdsDTO addAds(Properties properties, MultipartFile multipartFile) {
    log.info(FormLogInfo.getInfo());

    AdsDTO adsDTO = new AdsDTO();
    adsDTO.setTitle(properties.getTitle());
    adsDTO.setPrice(properties.getPrice());
    List<String> listOfImage = new ArrayList<>();
    String content = null;
    try {
      content = new String(multipartFile.getBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      log.error(FormLogInfo.getCatch());
    }
    listOfImage.add(content);
    adsDTO.setImage(listOfImage);
    adsRepository.save(adMapper.toEntity(adsDTO));
    return adsDTO;
  }

  /**
   * Добавление фото в объявление
   * @param id
   * @param image
   */
  @Override
  public void uploadImage(Integer id, MultipartFile image) throws IOException {
    log.info(FormLogInfo.getInfo());
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);

    Path filePath = Path.of(imageDir, adEntity.getId() + "." + getExtension(image.getOriginalFilename()));
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
//    pet.setFilePath(filePath.toString());
//    pet.setFileSize(image.getSize());
//    pet.setMediaType(image.getContentType());
//    pet.setPhoto(image.getBytes());
    imageRepository.save(imageEntity);
//    adsRepository.save(adEntity);
  }

  /**
   * вспомогательный медот для загрузки фотографий
   *
   * @return расширение файла
   */
  private String getExtension(String fileName) {
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }


  @Override
  public CommentDTO getComments(String adPk, int id) {
    return null;
    //return new CommentDTO(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
  }

  @Override
  public CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO) {
//    CommentEntity commentEntity = commentRepository.findByIdAndPk_Pk(id, adPk);
//    UserEntity author = userRepository.findById(commentDTO.)
//    commentEntity.setAuthor();
    return null;
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
  public AdsDTO getAds(int id) {
    return new AdsDTO();
  }

  @Override
  public AdsDTO updateAds(int id, CreateAds createAds) {
    AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
    adEntity.setDescription(createAds.getDescription());
    adEntity.setPrice(createAds.getPrice());
    adEntity.setTitle(createAds.getTitle());
    return null;
//    TODO: Восстановить, когда появится adMapper
//    AdsDTO savedAdsDTO = adMapper.toDTO(adsRepository.save(adEntity));
//    return savedAdsDTO;
  }



}
