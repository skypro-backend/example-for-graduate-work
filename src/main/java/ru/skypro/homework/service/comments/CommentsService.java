package ru.skypro.homework.service.comments;

import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

public interface CommentsService {

    CommentsDto getComments(Integer id);

    CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    void deleteComment(Integer adId, Integer commentId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
