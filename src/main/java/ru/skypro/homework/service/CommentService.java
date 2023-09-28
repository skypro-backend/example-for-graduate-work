package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {
    CommentsDto getAllCommentsForAdById(Integer adPk);
    CommentDto createNewComment(Integer adPk, CreateOrUpdateCommentDto createOrUpdateCommentDto, String username);
    void deleteComment (Integer adPk, Integer commentPk);
    CommentDto updateComment (Integer adPk, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
