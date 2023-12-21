package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;

public class CommentServiceImpl implements CommentService {
    @Override
    public CommentsDTO getComments(Integer adId) {
        return null;
    }

    @Override
    public CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return null;
    }

    @Override
    public Void deleteComment(Integer adId, Long commentId) {
        return null;
    }

    @Override
    public CommentDTO patchComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return null;
    }
}
