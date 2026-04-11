package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Интерфейс для операций с комментариями.
 * Включает получение комментариев по объявлению, добавление, обновление и удаление комментариев.
 */
public interface CommentService {

    Comments getCommentsByAdId(Integer adId);

    Comment addComment(Integer adId, CreateOrUpdateComment commentRequest, String authorEmail);

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentRequest);

    void deleteComment(Integer adId, Integer commentId);
}