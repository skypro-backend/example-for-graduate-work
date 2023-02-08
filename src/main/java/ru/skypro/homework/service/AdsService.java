package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;

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
}
