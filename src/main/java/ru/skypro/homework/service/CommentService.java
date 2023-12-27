package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;


import java.util.List;

public interface CommentService {

    List<Comment> getAllByCommentById(Long id);
    ResponseEntity<Void> createComment(ru.skypro.homework.model.Comment comment);

    Comment createComment(CreateOrUpdateCommentDTO text);

    Comment findById(Long commentId);
    ResponseEntity<Comment> deleteComment(Long adId, Long commentId);
    ResponseEntity<Comment> updateComment(Long adId, Long commentId, CreateOrUpdateCommentDTO text);
}
