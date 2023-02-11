package ru.skypro.homework.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.service.AdsService;

/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
public class AdsServiceImpl implements AdsService {

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
  public Map<String, Object> getALLAds() {
    return Map.of("count", 1, "result",
        List.of(new AdsDTO(1L, "sda", 1, 2, 3, "sad")));
  }

  @Override
  public AdsDTO addAds(AdsDTO ad) {
    return new AdsDTO(1L, "sda", 1, 2, 3, "sad");
  }

  @Override
  public Map<String, Object> getAdsMe(boolean authenticated, String authorities, Object credentials,
      Object details, Object principal) {
    return Map.of("count", 1, "result",
        List.of(new AdsDTO(1L, "sda", 1, 2, 3, "sad")));
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
