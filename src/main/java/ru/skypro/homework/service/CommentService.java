package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;

/**
 * Сервис для управления комментариями к объявлениям.
 * Обеспечивает бизнес-логику получения, добавления, удаления и изменения комментариев.
 */
public interface CommentService {

    /**
     * Получает список всех комментариев, привязанных к конкретному объявлению.
     *
     * @param adId уникальный идентификатор объявления
     * @return объект {@link Comments}, содержащий количество и список комментариев
     */
    Comments getComments(Integer adId);

    /**
     * Добавляет новый комментарий к указанному объявлению от имени авторизованного пользователя.
     *
     * @param adId       уникальный идентификатор объявления
     * @param properties текст создаваемого комментария {@link CreateOrUpdateComment}
     * @param username   логин (email) автора комментария
     * @return DTO созданного комментария {@link CommentDto}
     */
    CommentDto addComment(Integer adId, CreateOrUpdateComment properties, String username);

    /**
     * Удаляет комментарий из системы.
     *
     * @param adId      уникальный идентификатор объявления
     * @param commentId уникальный идентификатор удаляемого комментария
     */
    void deleteComment(Integer adId, Integer commentId);

    /**
     * Обновляет текст существующего комментария.
     *
     * @param adId       уникальный идентификатор объявления
     * @param commentId  уникальный идентификатор обновляемого комментария
     * @param properties новые данные комментария {@link CreateOrUpdateComment}
     * @return DTO обновленного комментария {@link CommentDto}
     */
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment properties);
}