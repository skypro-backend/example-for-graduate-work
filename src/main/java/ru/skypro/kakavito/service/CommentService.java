package ru.skypro.kakavito.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.kakavito.dto.CommentsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateCommentDTO;
import ru.skypro.kakavito.model.Comment;

public interface CommentService {

    CommentsDTO getAllByCommentById(int id);
    Comment createComment(CreateOrUpdateCommentDTO text);
    Comment findById(Long commentId);
    ResponseEntity<Comment> deleteComment(Long adId, Long commentId);
    Comment updateComment(Long adId, Long commentId, CreateOrUpdateCommentDTO text);
}
