package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public AdsDto getAds(int id) {
       return new AdsDto();
    }
    @Override
    public AdsDto updateAds( int id) {

    }

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import ru.skypro.homework.record.AdRecord;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;


import java.util.Collection;


/**
 * Реализация {@link ru.skypro.homework.service.AdsService}
 */
@Service
public class AdsServiceImpl implements AdsService {

    /**
     * Получение всех комментариев объявления
     * @param pk
     * @return
     */
    @Override
    public Collection<Comment> getAdsComments(Integer pk) {
        return null;
    }

    /**
     * Добавление коментария к объявлению
     * @param pk
     */
    @Override
    public void addAdsComments(Integer pk) {

    }

    /**
     * Удаление комментария конкретного пользователя у объявления
     * @param pk
     * @param id
     */
    @Override
    public void deleteAdsComment(Integer pk, Integer id) {

    }


  @Override
  public Map<String, Object> getALLAds() {
    return Map.of("count", 1, "result",
        List.of(new AdRecord(1L, "sda", 1, 2, 3, "sad")));
  }

  @Override
  public AdRecord addAds(AdRecord ad) {
    return new AdRecord(1L, "sda", 1, 2, 3, "sad");
  }

  @Override
  public Map<String, Object> getAdsMe(boolean authenticated, String authorities, Object credentials,
      Object details, Object principal) {
    return Map.of("count", 1, "result",
        List.of(new AdRecord(1L, "sda", 1, 2, 3, "sad")));
  }

    @Override
    public Comment getComments(String adPk, int id) {
        return new Comment(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
    }

    @Override
    public void deleteComments(String adPk, int id) {
    }

    @Override
    public Comment updateComments(String adPk, int id, Comment comment) {
        return comment;
    }

    @Override
    public void removeAds(int id) {
    }

}
