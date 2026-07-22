package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getComments(Integer adId);

    Comment addComment(Integer adId, CreateOrUpdateComment comment);

    void deleteComment(Integer adId, Integer commentId);

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);
}