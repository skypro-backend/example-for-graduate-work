package ru.skypro.homework.service;

import org.springframework.security.core.Authentication; // Исправлено
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Long adId);
    Comment addComment(Long adId, CreateOrUpdateComment text, Authentication authentication);
    void deleteComment(Long adId, Long commentId, Authentication authentication);
    Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment text, Authentication authentication);
}
