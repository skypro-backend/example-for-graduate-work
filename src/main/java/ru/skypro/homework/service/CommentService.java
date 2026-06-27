package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Интерфейс бизнес-логики для управления комментариями к объявлениям.
 */
public interface CommentService {
    /**
     * Получает список всех комментариев, привязанных к конкретному объявлению.
     *
     * @param adId уникальный идентификатор объявления
     * @return объект {@link Comments} со списком комментариев и их количеством
     */
    Comments getComments(Integer adId);

    /**
     * Добавляет новый комментарий к объявлению от имени авторизованного пользователя.
     *
     * @param adId уникальный идентификатор объявления
     * @param dto данные создаваемого комментария
     * @param username имя автора комментария (email)
     * @return созданный комментарий в виде DTO {@link Comment}
     */
    Comment addComment(Integer adId, CreateOrUpdateComment dto, String username);

    /**
     * Удаляет комментарий по его идентификатору.
     *
     * @param commentId уникальный идентификатор комментария
     */
    void deleteComment(Integer commentId);

    /**
     * Обновляет текст существующего комментария.
     *
     * @param commentId уникальный идентификатор комментария
     * @param dto новые текстовые данные для комментария
     * @return обновленный комментарий в виде DTO {@link Comment}
     */
    Comment updateComment(Integer commentId, CreateOrUpdateComment dto);

    /**
     * Проверяет, является ли пользователь автором данного комментария или администратором.
     * Используется для проверки прав доступа на уровне эндпоинтов.
     *
     * @param commentId уникальный идентификатор комментария
     * @param username имя пользователя (email) для проверки прав
     * @return {@code true}, если доступ разрешен, иначе {@code false}
     */
    boolean isCommentAuthorOrAdmin(Integer commentId, String username);
}