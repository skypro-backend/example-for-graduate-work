package ru.skypro.homework.service.comments.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.comments.CommentsService;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Override
    public CommentsDto getComments(Integer adId) {
        return null;
    }

    @Override
    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto) {

        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {

        return null;
    }
}
