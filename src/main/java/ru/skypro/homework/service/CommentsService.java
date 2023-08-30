package ru.skypro.homework.service;

import ru.skypro.homework.dto.comments.CommentDto;

import java.util.List;

public interface CommentsService {

    List<CommentDto> getComments(int adId);

    CommentDto addComment(int adId, CommentDto commentDto);

    void deleteCommentByAdIdAndCommentId(int adId, int commentId);

    CommentDto updateComment(int adId, int commentId, CommentDto commentDto);
}
