package ru.skypro.homework.service;

import java.util.Map;
import ru.skypro.homework.dto.Comment;

import java.util.Collection;
import ru.skypro.homework.record.AdRecord;
import ru.skypro.homework.dto.AdsDto;

/**
 * Сервис объявлений
 */
public interface AdsService {
    /**
     * Возвращает объявление
     * @param id    - идентификатор объявления
     * @return      - комментарий
     */
    AdsDto getAds(int id);
    /**
     * Обновляет объявление
     * @param id      - идентификатор объявления
     * @return          - обнволенный комментарий
     */
    AdsDto updateAds( int id);
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


    Collection<Comment> getAdsComments (Integer pk);


    void addAdsComments (Integer pk);


    void deleteAdsComment (Integer pk, Integer id);

    /**
     * Возвращает все объявления
     *
     * @return мапу где ключ по имени столбца в таблице каунт и резалт
     */
    Map<String, Object> getALLAds();

    /**
     * Добавляем новое объявление
     *
     * @return мапу где ключ по имени столбца в таблице каунт и резалт
     */
    AdRecord addAds(AdRecord adRecord);


    /**
     * Возвращает объявления(е) по параметрам
     *
     * @return мапу где ключ по имени столбца в таблице каунт и резалт
     */
    Map<String, Object> getAdsMe(boolean authenticated, String authorities, Object credentials,
        Object details,
        Object principal);
}
