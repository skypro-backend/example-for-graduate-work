package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comment createComment(Long adId, CreateOrUpdateComment dto, Authentication authentication);

    Comments getCommentsByAdId(Long adId);

    Comment updateComment(Long commentId, CreateOrUpdateComment dto, Authentication authentication);

    void deleteComment(Long commentId, Authentication authentication);
}
