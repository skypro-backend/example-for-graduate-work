package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.request.CommentRequestDto;
import ru.skypro.homework.dto.response.CommentResponseDto;
import ru.skypro.homework.dto.response.CommentsResponseDto;

public interface CommentService {
    CommentsResponseDto getComments(Integer adId);

    CommentResponseDto addComment(Integer adId, CommentRequestDto dto);

    CommentResponseDto updateComment(Integer adId, Integer commentId, CommentRequestDto dto);

    void deleteComment(Integer adId, Integer commentId);

    boolean isCommentOwner(Integer commentId, Authentication authentication);
}
