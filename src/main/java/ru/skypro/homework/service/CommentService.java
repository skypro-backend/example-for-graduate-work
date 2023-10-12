package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;


public interface CommentService {
    CommentsDTO getComments(Authentication authentication, int id);
    CommentDTO addComment(Authentication authentication, int id, CreateOrUpdateCommentDTO comment);
    void deleteComment(Authentication authentication,int adId, int commentId);
    CommentDTO updateComment(Authentication authentication, int adId, int commentId, CreateOrUpdateCommentDTO createOrUpdateComment);


}
