package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Override
    public List<CommentDto> getComments(int adId) {
        return null;
    }

    @Override
    public CommentDto addComment(int adId, CommentDto commentDto) {

        return commentDto;
    }

    @Override
    public void deleteCommentByAdIdAndCommentId(int adId, int commentId) {

    }

    @Override
    public CommentDto updateComment(int adId, int commentId, CommentDto commentDto) {

        return commentDto;
    }
}
