package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;

import java.util.Collection;


public interface AdsService {

    Collection<Comment> getAdsComments (Integer pk);


    void addAdsComments (Integer pk);


    void deleteAdsComment (Integer pk, Integer id);



=======
/**
 * Сервис объявлений
 */
public interface AdsService {
    /**
     * Возвращает комментарий
      * @param adPk - идентификатор объявления
     * @param id    - идентификатор комментария
     * @return      - комментарий
     */
    Comment getComments(String adPk, int id);

    /**
     * Удаляет комментарий
     * @param adPk  - идентификатор объявления
     * @param id    - идентификатор комментария
     */
    void deleteComments(String adPk, int id);

    /**
     * Обновляет комментарий
     * @param adPk      - идентификатор объявления
     * @param id        - идентификатор комментария
     * @param comment   - новый комментарий
     * @return          - обнволенный комментарий
     */
    Comment updateComments(String adPk, int id, Comment comment);

    void removeAds(int id);
}
