package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.Properties;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
@Slf4j
public class AdsServiceImpl implements AdsService {

  private final AdsRepository adsRepository;
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private AdMapper adMapper;
  private CommentMapper commentMapper;

  public AdsServiceImpl(AdsRepository adsRepository, CommentRepository commentRepository,
                        UserRepository userRepository, AdMapper adMapper, CommentMapper commentMapper) {
    this.adsRepository = adsRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.adMapper = adMapper;
    this.commentMapper = commentMapper;
  }

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
    AdEntity adEntity = adsRepository.findById(1).orElseThrow(ElemNotFound::new);
    CommentEntity comment = commentRepository.findById(1).orElseThrow(ElemNotFound::new);
    if (Objects.equals(adEntity.getAuthor().getId(), comment.getAuthor().getId())) {
      commentRepository.deleteById(comment.getId());
    }
    throw new ElemNotFound();
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


  @Override
  public CommentDTO getComments(String adPk, int id) {
    return null;
    //return new CommentDTO(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
  }

  @Override
  public CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO) {
    CommentEntity commentEntity = commentRepository.findByIdAndAd_Id(id, adPk).orElseThrow(ElemNotFound::new);

    UserEntity author = userRepository.findById(commentDTO.getAuthor()).orElseThrow(ElemNotFound::new);
    commentEntity.setAuthor(author);

    commentEntity.setText(commentDTO.getText());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    commentEntity.setCreatedAt(LocalDateTime.parse(commentDTO.getCreatedAt(),formatter));

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
  public AdsDTO getAds(int id) {
    return new AdsDTO();
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
