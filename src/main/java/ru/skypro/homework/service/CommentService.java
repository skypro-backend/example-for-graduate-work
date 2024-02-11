package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import java.security.Principal;
import java.util.Collection;

public interface CommentService {
    Collection<CommentsDTO> getAllComments(int id);
    CommentEntity getComment(long commentId);

    CommentDTO addComment(int id, CreateOrUpdateCommentDTO comment, Authentication authentication);

    void deleteComment(int adId, int commentId);
    void deleteAllByAdId(long adId);
    CommentDTO updateComment(int adId, int commentId, CommentDTO comment);
}
