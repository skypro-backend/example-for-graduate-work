package ru.skypro.homework.service.impl;

import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long adId, long commentId) {
        commentRepository.deleteByAdIdAndId(adId, commentId);
    }

    @Override
    public Comment editComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(long adId) {
        return commentRepository.findAllByAdId(adId);
    }
}
