package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.nio.file.AccessDeniedException;

public interface CommentsService {

    CommentsDTO getComments(long id);

    CommentDTO addComment(long id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);

    void deleteComment(long listingId, long commentId, Authentication authentication) throws AccessDeniedException;

    CommentDTO updateComment(long listingId, long commentId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) throws AccessDeniedException;

}
