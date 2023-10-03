package ru.skypro.homework.service.comment;


import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    List<Comments> getAllCommentsByAdId(Integer id);
    CommentDTO createComment(Integer id, CreateOrUpdateComment comment);
    void deleteComment(Integer commentId, Integer adId);
    CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);

}
