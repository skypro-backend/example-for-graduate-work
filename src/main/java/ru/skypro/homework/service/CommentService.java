package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import java.security.Principal;

public interface CommentService {
    CommentEntity findById(Integer id);
    CommentDTO createComment(Integer adId, CreateOrUpdateCommentDTO createOrUpdateCommentDto, Principal principal);

    CommentsDTO getComments(Integer adId);
    CommentDTO editComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDto);
    HttpStatus deleteComment(Integer adId, Integer commentId);
}
