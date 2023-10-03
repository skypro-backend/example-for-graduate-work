package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;
/**
 * CRUD-methods for managing Comments on platform
 */
public interface CommentService {

    /**
     * Method creates new comment.
     * @param id Ads ID
     * @param commentDto DTO of the new comment
     * @param authentication Authentication data.
     * @return Returns a new comment.
     */
    CommentDto create (Integer id, CommentDto commentDto,
                             Authentication authentication);

    /**
     * Method updates the old comment
     * @param adId ID of the add
     * @param commentId ID of the comment to be updated
     * @param commentDto DTO of the new comment
     * @return Returns the updated comment
     */
    CommentDto update(Integer adId, Integer commentId, CommentDto commentDto);

    /**
     * Method to get list of comments by adds ID
     * @param id ID of the add
     * @return Returns the list of comments of an add
     */

    List<CommentDto> get(Integer id);

    /**\
     * Method to get comment by ID
     * @param id ID of comment to search
     * @return Returns the comment by ID
     */

    Comment getById(Integer id);

    /**
     * Method to remove the comment
     * @param adId ID of an add
     * @param commentId ID of the comment to be removed
     */

    void remove(Integer adId, Integer commentId);
}
