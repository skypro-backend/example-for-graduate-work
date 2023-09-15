package ru.skypro.homework.service.comments;

import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.dto.comments.in.CreateOrUpdateCommentDto;

public interface CommentsService {

    CommentsDto getComments(Integer id);

    CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    void deleteComment(Integer adId, Integer commentId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
