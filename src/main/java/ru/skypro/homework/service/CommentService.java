package ru.skypro.homework.service;

import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateComment;

public interface CommentService {
    CommentsDto getComments(int id);

    CommentDto addComment(int id, CreateOrUpdateComment text, String userName);

    void deleteComment(int adId, int commentId, String userName);

    CommentDto updateComment(int adId, int commentId, CreateOrUpdateComment text, String userName);
}
