package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {

    /**
     * Создание комментария, по id
     *
     * @param id                    {@link Integer}
     * @param createOrUpdateComment {@link CreateOrUpdateComment}
     * @param username              {@link String}
     * @return объект {@link Comment}
     */
    Comment addComment(int id, CreateOrUpdateComment createOrUpdateComment, String username);

    /**
     * Получение всех комментариев, по id, в List</>
     *
     * @param id {@link Integer}
     * @return объект {@link Comments}
     */
    Comments getComments(int id, String username);

    /**
     * Редактирование комментария по его id
     *
     * @param adId                  {@link Integer}
     * @param commentId             {@link Integer}
     * @param createOrUpdateComment {@link CreateOrUpdateComment}
     * @return объект {@link Comment}
     */
    Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment,
                          String username);

    /**
     * Удаление комментария по его id
     *
     * @param adId      {@link Integer}
     * @param commentId {@link Integer}
     * @param username  {@link String}
     */
    void deleteComment(int adId, int commentId, String username);

}