package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentsDto getComments(Integer adId);

    CommentDto addComment(String email, Integer adId, CreateOrUpdateCommentDto createComment);

    CommentDto updateComment(String email, Integer adId, Integer commentId, CreateOrUpdateCommentDto updateComment);

    void deleteComment(String email, Integer adId, Integer commentId);
}
