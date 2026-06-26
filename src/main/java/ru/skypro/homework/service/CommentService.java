package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;

public interface CommentService {
    Comments getComments(Integer adId);
    CommentDto addComment(Integer adId, CreateOrUpdateComment properties, String username);
    void deleteComment(Integer adId, Integer commentId);
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment properties);
}