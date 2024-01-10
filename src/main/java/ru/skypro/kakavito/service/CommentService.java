package ru.skypro.kakavito.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.CommentsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateCommentDTO;
import ru.skypro.kakavito.model.Comment;

public interface CommentService {

    //    CommentsDTO getAllByCommentsById(int id);
    CommentsDTO getAllByCommentById(int id);

    CommentDTO createComment(int adId, CreateOrUpdateCommentDTO text);

//    CommentDTO findById(int commentId);
    void deleteComment(int adId, int commentId);
    CommentDTO updateComment(int adId, int commentId, CreateOrUpdateCommentDTO text);
}
