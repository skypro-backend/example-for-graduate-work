package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

/**
 * Сервис для работы с комментариями
 */
public interface CommentService {

    /**
     * Получение комментария по id объявления
     *
     * @param adId id объявления
     * @return ДТО комментария
     */
    CommentsDto getCommentsByAdId(Integer adId);

    /**
     * Добавление комментария к объявлению
     *
     * @param adId id объявления
     * @param dto  ДТО комментария
     * @return ДТО добавленного комментария
     */
    CommentDto addCommentToAd(Integer adId, CreateOrUpdateCommentDto dto);

    /**
     * Удаление комментария
     *
     * @param id   id комментария
     * @param adId id объявления
     */
    void deleteComment(Integer id, Integer adId);

    /**
     * Редактирование комментария
     *
     * @param id   id комментария
     * @param adId id объявления
     * @param dto  ДТО комментария
     * @return ДТО отредактированного комментария
     */
    CommentDto updateComment(Integer id, Integer adId, CreateOrUpdateCommentDto dto);

    /**
     * Проверка наличия комментария по id и логину пользователя
     *
     * @param id       id комментария
     * @param username Логин пользователя
     * @return Комментарий существует или нет
     */
    boolean existsCommentByIdAndUsername(Integer id, String username);

}

