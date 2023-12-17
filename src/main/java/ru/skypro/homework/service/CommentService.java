package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentService {
    CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO comment);

    CommentsDTO getComments(Integer adId);

    void deleteComment(Integer adId, Integer commentId);

    CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO commentDTO);
}
