package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentService {
    CommentsDTO getComments(Integer adId);

    CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

    Void deleteComment(Integer adId, Integer commentId);

    CommentDTO patchComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
