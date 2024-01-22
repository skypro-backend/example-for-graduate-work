package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {
    CommentsDto getComments(long id);

    CommentDto addComment(long id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);

    void deleteComment(long adId, long commentId, Authentication authentication);

    CommentDto updateComment(long adId, long commentId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication);
}

