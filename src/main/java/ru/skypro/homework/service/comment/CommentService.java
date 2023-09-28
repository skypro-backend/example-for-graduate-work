package ru.skypro.homework.service.comment;


import org.springframework.http.ResponseEntity;
import ru.skypro.homework.projection.CreateOrUpdateComment;

public interface CommentService {

    ResponseEntity<?> getAllCommentsByAdId(Integer id);
    ResponseEntity<?> createComment(Integer id, CreateOrUpdateComment comment);
    ResponseEntity<?> deleteComment(Integer adId, Integer commentId);
    ResponseEntity<?> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);

}
