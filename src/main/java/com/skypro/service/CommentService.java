package com.skypro.service;

import com.skypro.dto.Comment;
import com.skypro.dto.Comments;
import com.skypro.dto.CreateOrUpdateComment;

/**
 * Сервис для управления комментариями.
 */
public interface CommentService {

    /**
     * Возвращает список комментариев для указанного объявления.
     *
     * @param adId идентификатор объявления
     * @return объект {@link Comments} с количеством и списком комментариев
     */
    Comments getComments(Integer adId);

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId                   идентификатор объявления
     * @param createOrUpdateComment  текст комментария
     * @param username               логин автора (из аутентификации)
     * @return созданный комментарий в виде {@link Comment}
     */
    Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username);

    /**
     * Удаляет комментарий по идентификатору.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId      идентификатор объявления (контекст)
     * @param commentId идентификатор комментария
     */
    void deleteComment(Integer adId, Integer commentId);

    /**
     * Обновляет текст существующего комментария.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId                   идентификатор объявления (контекст)
     * @param commentId              идентификатор комментария
     * @param createOrUpdateComment  новый текст
     * @return обновлённый комментарий в виде {@link Comment}
     */
    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment);
}