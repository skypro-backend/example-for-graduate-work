package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {
    CommentDto addCommentToAd(Integer adId,
                              CreateOrUpdateCommentDto createOrUpdateCommentDto,
                              Authentication authentication);

    CommentDto updateCommentToAd(Integer adId,
                                 Integer commentId,
                                 CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                 Authentication authentication);

    void removeComment(Integer adId, Integer commentId, Authentication authentication);

    CommentsDto getCommentsByAd(Integer adId);
}
