package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@Service
public class CommentsService {
    public CommentsDto getCommentsById(Integer id) {
        return null;
    }

    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto text) {
        return null;
    }

    public void deleteComment(Integer adId, Integer commentId) {
    }

    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto newComment) {
        return null;
    }
}
