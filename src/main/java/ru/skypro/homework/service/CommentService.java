package ru.skypro.homework.service;

import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.Comments;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import org.springframework.security.core.Authentication;

    //* Сервис для работы с комментариями к объявлениям.

public interface CommentService {

    //      * Получает все комментарии для конкретного объявления.
    //      * @param adId идентификатор объявления
    //      * @return коллекцию комментариев Comments

    Comments getCommentsForAd(long adId);

    //     * Возвращает комментарии, написанные текущим пользователем.
    //     * @param auth объект аутентификации
    //     * @return коллекцию комментариев пользователя

    Comments getMyAds(Authentication auth);

    //     * Добавляет новый комментарий к объявлению.
    //     * @param adId идентификатор объявления
    //     * @param data текст комментария
    //     * @param auth данные аутентификации автора
    //     * @return созданный объект Comment

    Comment createComment(long adId, CreateOrUpdateComment data, Authentication auth);

    //     * Редактирует существующий комментарий.
    //     * Проверяет права доступа: редактировать может только автор или ADMIN.
    //     * @param adId идентификатор родительского объявления
    //     * @param commentId идентификатор комментария
    //     * @param data новый текст комментария
    //     * @param auth данные аутентификации
    //     * @return обновленный объект Comment

    Comment updateComment(long adId, long commentId, CreateOrUpdateComment data, Authentication auth);

    //     * Удаляет комментарий.
    //     * Проверяет права доступа: удалить может только автор или ADMIN.
    //     * @param adId идентификатор родительского объявления
    //     * @param commentId идентификатор комментария
    //     * @param auth данные аутентификации

    void deleteComment(long adId, long commentId, Authentication auth);

}