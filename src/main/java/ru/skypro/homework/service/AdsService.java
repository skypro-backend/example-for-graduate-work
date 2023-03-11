package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;


/**
 * Сервис объявлений
 */
public interface AdsService {

  /**
   * Возвращает объявление
   *
   * @param id - идентификатор объявления
   * @return - комментарий
   */
  FullAds getAdById(int id, Authentication authentication);

  /**
   * Обновляет объявление
   *
   * @param id - идентификатор объявления
   * @return - обнволенный комментарий
   */
  AdsDTO updateAds(int id, CreateAds createAd, Authentication authentication);

  /**
   * Возвращает комментарий
   *
   * @param adPk - идентификатор объявления
   * @param id   - идентификатор комментария
   * @return - комментарий
   */
  CommentDTO getComments(int adPk, int id);

  /**
   * Удаляет комментарий
   *
   * @param adPk - идентификатор объявления
   * @param id   - идентификатор комментария
   */
  void deleteComments(Integer adPk, Integer id, Authentication authentication);

  /**
   * Обновляет комментарий
   *
   * @param adPk            - идентификатор объявления
   * @param id              - идентификатор комментария
   * @param commentDTO      - новый комментарий
   * @param authentication  - аутентификация
   * @return - обнволенный комментарий
   */
  CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO, Authentication authentication);

  void removeAds(int id, Authentication authentication);


  ResponseWrapperComment getAdsComments(Integer pk);


  /**
   * Добавляем комментарий к объявлению
   */
  CommentDTO addAdsComments(Integer pk, CommentDTO commentDTO, Authentication authentication);


  /**
   * @return все объявления
   */
  ResponseWrapperAds getAds();

  /**
   * Добавляем новое объявление
   *
   * @return возвращает созданное объявление
   */
  AdsDTO addAds(CreateAds createAds, MultipartFile multipartFile, Authentication authentication)
      throws IOException;

  /**
   * Добавление фото в объявление
   *
   * @param id
   * @param image
   */
  void uploadImage(Integer id, MultipartFile image) throws IOException;

  /**
   * Получаем только свои объявления
   *
   * @param authentication данные о пользователе
   * @return общий подсчет своих объявлений + объявления
   */
  ResponseWrapperAds getAdsMe(Authentication authentication);

  /**
   * получить аватарку объявления
   * @param id объявления
   * @return байтовое представление картинки
   */
  byte[] getPhotoById(Integer id);
}
