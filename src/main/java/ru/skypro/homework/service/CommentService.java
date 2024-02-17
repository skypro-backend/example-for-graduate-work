package ru.skypro.homework.service;


import ru.skypro.homework.dto.Comment;

public interface CommentService {

    Comment getComments(Integer id);

    Comment addComment(Integer id, String username);

    String deleteComment(Integer commentId, String username);

    Comment updateComment(Integer commentId, String username);
}