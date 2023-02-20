package ru.skypro.homework.service;


import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.dto.ResponseWrapperComment;


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
  FullAds getAdById(int id);

  /**
   * Обновляет объявление
   *
   * @param id - идентификатор объявления
   * @return - обнволенный комментарий
   */
  AdsDTO updateAds(int id, CreateAds createAd);

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
  void deleteComments(Integer adPk, Integer id);

  /**
   * Обновляет комментарий
   *
   * @param adPk       - идентификатор объявления
   * @param id         - идентификатор комментария
   * @param commentDTO - новый комментарий
   * @return - обнволенный комментарий
   */
  CommentDTO updateComments(int adPk, int id, CommentDTO commentDTO);

  void removeAds(int id);


  ResponseWrapperComment getAdsComments(Integer pk);


  CommentDTO addAdsComments(Integer pk,CommentDTO  commentDTO,Authentication authentication);


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
   * @param id
   * @param image
   */
  void uploadImage (Integer id, MultipartFile image) throws IOException;

}
