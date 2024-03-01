package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(Long adId);

    Comment addComment(Long adId, CreateOrUpdateComment comment);

    void deleteComment(Long adId, Long commentId);

    Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment comment);
}
