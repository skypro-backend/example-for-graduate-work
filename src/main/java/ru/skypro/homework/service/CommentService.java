package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Сервис для работы с комментариями к объявлениям.
 */

public interface CommentService {

    /** Возвращает все комментарии объявления. */

    Comments getComments(Integer adId);

    /** Добавляет комментарий от имени текущего пользователя. */

    Comment addComment(Integer adId, CreateOrUpdateComment dto, Authentication authentication);

    /**
     * Удаляет комментарий.
     *
     * @throws org.springframework.security.access.AccessDeniedException
     *         если текущий пользователь не автор комментария и не ADMIN
     */

    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

    /**
     * Обновляет текст комментария.
     *
     * @throws org.springframework.security.access.AccessDeniedException
     *         если текущий пользователь не автор комментария и не ADMIN
     */

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto, Authentication authentication);
}