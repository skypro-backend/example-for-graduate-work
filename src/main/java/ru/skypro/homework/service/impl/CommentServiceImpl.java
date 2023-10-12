package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public CommentsDTO getComments(Authentication authentication, int id) {
        return null;
    }

    @Override
    public CommentDTO addComment(Authentication authentication, int id, CreateOrUpdateCommentDTO comment) {
        return null;
    }

    @Override
    public void deleteComment(Authentication authentication, int adId, int commentId) {

    }

    @Override
    public CommentDTO updateComment(Authentication authentication, int adId, int commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return null;
    }
}
