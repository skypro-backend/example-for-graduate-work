package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Integer adId);
    Comment addComment(Integer adId, CreateOrUpdateComment dto, String username);
    void deleteComment(Integer commentId);
    Comment updateComment(Integer commentId, CreateOrUpdateComment dto);
    boolean isCommentAuthorOrAdmin(Integer commentId, String username);
}