package ru.skypro.homework.service.comment;


import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.projection.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllCommentsByAdId(Integer id);
    void createComment(Integer id, CreateOrUpdateComment comment);
    void deleteComment(Integer commentId, Integer adId);
    ResponseEntity<?> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);

}
