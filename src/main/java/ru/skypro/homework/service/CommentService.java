package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    Comments getComments(Integer adId);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    void addComment(Integer id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    void updateComment(Integer adId, Integer commentId,CreateOrUpdateComment createOrUpdateComment, Authentication authentication);
}
