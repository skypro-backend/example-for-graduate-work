package ru.skypro.homework.service;

import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    void deleteComment(long adId, long commentId);
    Comment editComment(Comment comment);
    List<Comment> getComments(long adId);
}
