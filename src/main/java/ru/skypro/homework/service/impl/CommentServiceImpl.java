package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Comments getComments(int adId) {
        return null;
    }

    @Override
    public Comment addComment(int adId, CreateOrUpdateComment commentDto) {
        return null;
    }

    @Override
    public void deleteComment(int adId, int commentId) {
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment commentDto) {
        return null;
    }
}