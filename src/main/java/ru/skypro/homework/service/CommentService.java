package ru.skypro.homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentService {


    CommentsDTO receivingAdComments(int adId);

    @PreAuthorize("@mySecurityService.canDeleteComment(#commentId, #adId)")
    void deleteComment(int adId, int commentId);

    CommentDTO addComment(int adId, CreateOrUpdateCommentDTO text);

    @PreAuthorize("@mySecurityService.canDeleteComment(#commentId, #adId)")
    CommentDTO updateComment(int adId, int commentId, CreateOrUpdateCommentDTO text);
}
