package ru.skypro.homework.service;

import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.PropertiesDTO;

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
  AdsDTO getAds(int id);

  /**
   * Обновляет объявление
   *
   * @param id - идентификатор объявления
   * @return - обнволенный комментарий
   */
  AdsDTO updateAds(int id);

  /**
   * Возвращает комментарий
   *
   * @param adPk - идентификатор объявления
   * @param id   - идентификатор комментария
   * @return - комментарий
   */
  CommentDTO getComments(String adPk, int id);

  /**
   * Удаляет комментарий
   *
   * @param adPk - идентификатор объявления
   * @param id   - идентификатор комментария
   */
  void deleteComments(String adPk, int id);

  /**
   * Обновляет комментарий
   *
   * @param adPk       - идентификатор объявления
   * @param id         - идентификатор комментария
   * @param commentDTO - новый комментарий
   * @return - обнволенный комментарий
   */
  CommentDTO updateComments(String adPk, int id, CommentDTO commentDTO);

  void removeAds(int id);


  Collection<CommentDTO> getAdsComments(Integer pk);


  void addAdsComments(Integer pk);


  void deleteAdsComment(Integer pk, Integer id);

  /**
   * @return все объявления
   */
  Collection<AdsDTO> getALLAds();

  /**
   * Добавляем новое объявление
   *
   * @return возвращает созданное объявление
   */
  AdsDTO addAds(PropertiesDTO properties, MultipartFile multipartFile);

}
