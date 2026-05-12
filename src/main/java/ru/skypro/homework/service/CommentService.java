package ru.skypro.homework.service;

import ru.skypro.homework.controller.CommentController;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.impl.CommentServiceImpl;

/**
 * Сервис для управления комментариями к объявлениям.
 * <p>
 * Определяет контракт бизнес-логики, связанной с комментариями:
 * <ul>
 *   <li>Получение всех комментариев объявления</li>
 *   <li>Добавление нового комментария</li>
 *   <li>Удаление и редактирование существующих комментариев</li>
 * </ul>
 * Все операции, кроме получения списка, требуют аутентификации.
 * Редактирование и удаление доступны только автору комментария или администратору.
 *
 * @see CommentServiceImpl — основная реализация интерфейса
 * @see CommentController — использует этот сервис для обработки HTTP-запросов
 * @see Comment — DTO для передачи данных комментария
 * @see Comments — DTO для возврата списка комментариев
 * @see CreateOrUpdateComment — DTO для создания и обновления комментария
 */
public interface CommentService {

    /**
     * Получает все комментарии к объявлению по его идентификатору.
     * <p>
     * Доступно без авторизации. Возвращает список комментариев,
     * отсортированных по дате создания (сначала новые).
     *
     * @param adId идентификатор объявления
     * @return объект {@link Comments}, содержащий общее количество и список комментариев
     */
    Comments getComments(Integer adId);

    /**
     * Добавляет новый комментарий к объявлению.
     * <p>
     * Доступно только авторизованным пользователям. Принимает текст комментария
     * и сохраняет его с привязкой к объявлению и автору.
     *
     * @param adId                  идентификатор объявления
     * @param createOrUpdateComment DTO с текстом комментария
     * @param username              email текущего пользователя (автора)
     * @return добавленный объект {@link Comment}
     */
    Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username);

    /**
     * Удаляет комментарий по его идентификатору.
     * <p>
     * Доступно только автору комментария или администратору.
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @param email     email текущего пользователя
     * @throws EntityNotFoundException если комментарий или пользователь не найдены
     * @throws SecurityException       если нет прав на удаление
     */
    void deleteComment(Integer adId, Integer commentId, String email);

    /**
     * Частично обновляет текст комментария.
     * <p>
     * Доступно только автору или администратору.
     *
     * @param adId                  идентификатор объявления
     * @param commentId             идентификатор комментария
     * @param createOrUpdateComment DTO с новым текстом комментария
     * @param email                 email текущего пользователя
     * @return обновлённый объект {@link Comment}
     * @throws EntityNotFoundException если комментарий или пользователь не найдены
     * @throws SecurityException       если нет прав на редактирование
     */
    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String email);
}