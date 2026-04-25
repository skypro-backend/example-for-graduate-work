package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

public interface CommentService {
    Comments getComments(Integer adId);

    Comment addComment(Integer adId, Comment comment, String username);

    void deleteComment(Integer adId, Integer commentId, String email);

    Comment updateComment(Integer adId, Integer commentId, Comment comment, String email);
}