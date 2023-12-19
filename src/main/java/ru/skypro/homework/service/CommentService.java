package ru.skypro.homework.service;


import ru.skypro.homework.dto.*;

public interface CommentService {
    CommentsDto getComments(int adId) throws Exception;
    CommentDto addComment(int adId, CreateOrUpdateCommentDto commentDto) throws Exception;
    void deleteComment(int adId, int commentId) throws Exception;
    CommentDto updateComment(int adId, int commentId, CreateOrUpdateCommentDto commentDto) throws Exception;}