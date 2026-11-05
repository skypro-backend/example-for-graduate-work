package ru.skypro.homework.service;

import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

public interface CommentService {
    CommentsDto getAdComments(Integer adId);
    CommentDto addComment(String email, Integer adId, CreateOrUpdateCommentDto dto);
    void removeComment(String email, Integer id, Integer adId);
    CommentDto updateComment(String email, Integer id, Integer adId, CreateOrUpdateCommentDto dto);
}
