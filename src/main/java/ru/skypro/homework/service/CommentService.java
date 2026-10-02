package ru.skypro.homework.service;

import ru.skypro.homework.api.dto.Comment;
import ru.skypro.homework.api.dto.Comments;
import ru.skypro.homework.api.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getComments(Integer adId);

    Comment addComment(String username, Integer adId, CreateOrUpdateComment comment);

    void deleteComment(String username, Integer adId, Integer commentId);

    Comment updateComment(String username, Integer adId, Integer commentId, CreateOrUpdateComment comment);
}
