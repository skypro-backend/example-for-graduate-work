package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.Comment;

import java.util.List;

/**
 * Service for working with comments.
 */
public interface CommentService {
    /**
     * Returns all ad comments by ad id.
     *
     * @param adId ad id
     * @return all ad comments by ad id
     */
    List<Comment> getComments(Long adId);

    /**
     * Creates a new comment.
     *
     * @param adId    ad id
     * @param comment information about new comment
     * @return created comment
     */
    Comment addComment(Long adId, CreateOrUpdateComment comment);

    /**
     * Deletes the comment by ad id and comment id.
     *
     * @param adId      ad id
     * @param commentId comment id
     */
    void deleteComment(Long adId, Long commentId);

    /**
     * Updates the comment by ad id and comment id.
     *
     * @param adId      ad id
     * @param commentId comment id
     * @param comment   new information about comment
     * @return updated comment
     */
    Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment comment);
}
