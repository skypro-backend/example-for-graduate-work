package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public void deleteComment(long adId, long commentId) {
//        commentRepository.deleteByAdIdAndId(adId, commentId);
    }

    @Override
    public Comment editComment(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public List<Comment> getComments(long adId) {
        return commentRepository.findAll();
    }
}
