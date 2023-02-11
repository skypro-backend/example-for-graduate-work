package ru.skypro.homework.service.impl;

import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.PropertiesDTO;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
@Slf4j
public class AdsServiceImpl implements AdsService {

  private AdsRepository adsRepository;
  private AdMapper adMapper;

  public AdsServiceImpl(AdsRepository adsRepository, AdMapper adMapper) {
    this.adsRepository = adsRepository;
    this.adMapper = adMapper;
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
   * Удаление комментария конкретного пользователя у объявления
   *
   * @param pk
   * @param id
   */
  @Override
  public void deleteAdsComment(Integer pk, Integer id) {

  }

  @Override
  public Collection<AdsDTO> getALLAds() {
    log.info(FormLogInfo.getInfo());
    return adMapper.toDTOList(adsRepository.findAll());
  }

  @Override
  public AdsDTO addAds(PropertiesDTO properties, MultipartFile multipartFile){
    log.info(FormLogInfo.getInfo());
    AdsDTO adsDTO = new AdsDTO();
    adsDTO.setTitle(properties.getTitle());
    adsDTO.setPrice(properties.getPrice());
    List<String> listOfImage = new ArrayList<>();
    try {
      listOfImage.add(Arrays.toString(multipartFile.getBytes()));
    } catch (IOException e) {
      log.error(FormLogInfo.getCatch());
    }
    adsDTO.setImage(listOfImage);
    return adsDTO;
  }


  @Override
  public CommentDTO getComments(String adPk, int id) {
    return new CommentDTO(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
  }

  @Override
  public void deleteComments(String adPk, int id) {
  }

  @Override
  public CommentDTO updateComments(String adPk, int id, CommentDTO commentDTO) {
    return null;
  }

  @Override
  public void removeAds(int id) {
  }

  @Override
  public AdsDTO getAds(int id) {
    return new AdsDTO();
  }

  @Override
  public AdsDTO updateAds(int id) {
    return null;
  }

}
