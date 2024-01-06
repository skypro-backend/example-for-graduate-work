package ru.skypro.homework.service;


import ru.skypro.homework.dto.*;

public interface CommentService {
    CommentsDto getComments(Integer adId);
    CommentDto addComment(Integer adId, CreateOrUpdateCommentDto commentDto);
    void deleteComment(Integer adId, Integer commentId) ;
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDto);
     }