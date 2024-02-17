package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getComments(Integer id) {
        return null;
    }

    @Override
    public Comment addComment(Integer id, String username) {
        return null;
    }

    @Override
    public String deleteComment(Integer commentId, String username) {
        return null;
    }

    @Override
    public Comment updateComment(Integer commentId, String username) {
        return null;
    }
}