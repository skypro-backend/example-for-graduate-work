package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.Optional;

/**
 * Сервис для управления комментариями
 * <p>
 * Предоставляет методы для CRUD операций с комментариями,
 * а также проверки прав доступа к комментариям
 * </p>
 */
public interface CommentService {

    /**
     * Возвращает все комментарии к указанному объявлению
     *
     * @param adId идентификатор объявления
     * @return объект Comments со списком комментариев и их количеством
     * @throws RuntimeException если объявление не найдено
     */
    Comments getCommentsByAdId(Integer adId);

    /**
     * Добавляет новый комментарий к объявлению
     *
     * @param adId       идентификатор объявления
     * @param commentDto DTO с текстом комментария
     * @param userEmail  email автора (из контекста безопасности)
     * @return Optional с созданным комментарием
     * @throws RuntimeException если объявление или пользователь не найдены
     */
    Optional<Comment> addComment(Integer adId, CreateOrUpdateComment commentDto, String userEmail);

    /**
     * Удаляет комментарий по идентификатору
     *
     * @param commentId идентификатор комментария
     * @return true если комментарий успешно удален, false если не найден
     */
    boolean deleteComment(Integer commentId);

    /**
     * Обновляет текст комментария
     *
     * @param commentId  идентификатор комментария
     * @param commentDto DTO с обновленным текстом
     * @return Optional с обновленным комментарием
     * @throws RuntimeException если комментарий не найден
     */
    Optional<Comment> updateComment(Integer commentId, CreateOrUpdateComment commentDto);

    /**
     * Проверяет, является ли пользователь владельцем комментария
     *
     * @param commentId идентификатор комментария
     * @param email     email пользователя
     * @return true если пользователь является владельцем, false в противном случае
     */
    boolean isCommentOwner(Integer commentId, String email);
}