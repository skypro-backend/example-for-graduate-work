package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;

public class CommentServiceImpl implements CommentService {


    @Override
    public CommentEntity findById(Integer id) {
        return null;
    }

    @Override
    public CommentDTO createComment(Integer adId, CreateOrUpdateCommentDTO createOrUpdateCommentDto, Principal principal) {
        return null;
    }

    @Override
    public CommentsDTO getComments(Integer adId) {
        return null;
    }

    @Override
    public CommentDTO editComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDto) {
        return null;
    }

    @Override
    public HttpStatus deleteComment(Integer adId, Integer commentId) {
        return null;
    }
}
