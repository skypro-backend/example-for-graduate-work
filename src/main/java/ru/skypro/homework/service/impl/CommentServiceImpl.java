package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;


import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public List<Comment> getAllByCommentById(Long id) {
        return null;
    }

    @Override
    public Comment createComment(CreateOrUpdateCommentDTO text) {
        return null;
    }

    @Override
    public Comment findById(Long commentId) {
        return null;
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {

    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateCommentDTO text) {
        return null;
    }
}
