package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getComments(Integer adId);

    Comment addComment(Integer adId, CreateOrUpdateComment dto, Authentication authentication);

    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto, Authentication authentication);
}