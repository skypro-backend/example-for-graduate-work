package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import org.springframework.security.core.Authentication;

public interface CommentService {
    /**
     * Метод для получения всех комментарий к объявлению
     *
     * @param id идентификатор объявления
     * @return возвращает комментарии
     */
    CommentDto getComments(int id);

    /**
     * Метод для добавления комментария в объявление
     *
     * @param id   идентификатор объявления
     * @param text текст комментария
     * @return возвращает добавленный комментарий
     */
    CommentDto addComment(int id, CommentDto text);

    /**
     * Удаляет комментарий
     *
     * @param adId           идентификатор объявления
     * @param commentId      идентификатор комментария
     * @param authentication объект {@link Authentication}
     * @return <code>true</code> если комментарий удален, <code>false</code> в случае неудачи
     */
    boolean deleteComment(int adId, int commentId, Authentication authentication);

    /**
     * Обновляет комментарий
     *
     * @param adId           идентификатор объявления
     * @param commentId      идентификатор комментария
     * @param commentDto     объект {@link CommentDto}
     * @param authentication объект {@link Authentication}
     * @return возвращает обновленный комментарий
     */
    CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto, Authentication authentication);
}
