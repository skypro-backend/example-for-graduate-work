package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private Ad ad;

    public CommentServiceImpl(CommentRepository commentRepository, Ad ad) {
        this.commentRepository = commentRepository;
        this.ad=ad;
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment read(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new NoSuchElementException("This comment not found");
        }
        return comment.get();
    }

    public Comment update(Comment comment) {
        Optional<Comment> check = commentRepository.findById(comment.getAuthorId());
        if (check.isEmpty()) {
            throw new NoSuchElementException("This comment not found");
        }

        return commentRepository.save(comment);
    }

    public Comment delete(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new NoSuchElementException("This comment not found");
        }
        commentRepository.deleteById(id);

        Comment deleteComment = comment.get();

        return deleteComment;
    }



}
