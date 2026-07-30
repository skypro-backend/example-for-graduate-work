package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Сервис работы с комментариями.
 */
public interface CommentService {

    /**
     * Возвращает комментарии объявления.
     *
     * @param adId идентификатор объявления
     * @return комментарии объявления
     */
    Comments getComments(Long adId);

    /**
     * Добавляет комментарий к объявлению.
     *
     * @param adId идентификатор объявления
     * @param username логин автора
     * @param request текст комментария
     * @return созданный комментарий
     */
    Comment addComment(
            Long adId,
            String username,
            CreateOrUpdateComment request
    );

    /**
     * Обновляет комментарий.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param username логин пользователя
     * @param role роль пользователя
     * @param request новый текст
     * @return обновлённый комментарий
     */
    Comment updateComment(
            Long adId,
            Long commentId,
            String username,
            String role,
            CreateOrUpdateComment request
    );

    /**
     * Удаляет комментарий.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param username логин пользователя
     * @param role роль пользователя
     */
    void deleteComment(
            Long adId,
            Long commentId,
            String username,
            String role
    );
}
