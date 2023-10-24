package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;

public interface CommentService {
    Comment find(int commentId);

    CommentDto createComment(int advertId, CreateOrUpdateCommentDto dto);

    CommentsDto getAllCommentsAdvert(int advertId);

    CommentDto updateComment(int advertId, int commentId, CreateOrUpdateCommentDto dto);

    void deleteComment(int advertId, int commentId);
}