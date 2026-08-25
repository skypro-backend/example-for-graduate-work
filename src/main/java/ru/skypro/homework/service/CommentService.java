package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Сервис для управления комментариями.
 * Предоставляет методы для создания, получения, обновления и удаления комментариев.
 */
public interface CommentService {

    /**
     * Получает список комментариев к объявлению.
     *
     * @param adId ID объявления
     * @return DTO со списком комментариев
     */
    Comments getComments(Integer adId);

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId ID объявления
     * @param createOrUpdateComment DTO с текстом комментария
     * @param username имя автора комментария
     * @return DTO созданного комментария
     * @throws RuntimeException если объявление или пользователь не найдены
     */
    Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username);

    /**
     * Удаляет комментарий по его ID.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     * @param username имя пользователя, выполняющего операцию
     * @throws RuntimeException если комментарий не найден или не принадлежит объявлению
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является автором или администратором
     */
    void deleteComment(Integer adId, Integer commentId, String username);

    /**
     * Обновляет текст комментария.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     * @param createOrUpdateComment DTO с обновленным текстом
     * @param username имя пользователя, выполняющего операцию
     * @return DTO обновленного комментария
     * @throws RuntimeException если комментарий не найден или не принадлежит объявлению
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является автором или администратором
     */
    Comment updateComment(Integer adId, Integer commentId,
                          CreateOrUpdateComment createOrUpdateComment, String username);

    /**
     * Проверяет, является ли пользователь автором комментария или администратором.
     *
     * @param username имя пользователя
     * @param commentId ID комментария
     * @return true, если пользователь является автором или администратором
     */
    boolean isAuthor(String username, Integer commentId);
}