package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByAdId(Integer adId);

    Comment addComment(Integer adId, CreateOrUpdateComment commentRequest, String authorEmail);

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentRequest);

    void deleteComment(Integer adId, Integer commentId);
}