package ru.skypro.homework.service.impl;

import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentEntity create(CommentEntity comment) {
        if(commentRepository.findById(comment.getPk()).isPresent()){
            throw new RuntimeException("Comment exists");
        }
        return commentRepository.save(comment) ;
    }

    @Override
    public CommentEntity read(long id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new RuntimeException("Comment not found");
        }
        return comment.get();
    }

    @Override
    public CommentEntity update(CommentEntity comment) {
        if(commentRepository.findById(comment.getPk()).isEmpty()){
            throw new RuntimeException("Comment not found");
        }
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity delete(long id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(id);
        return comment.get();
    }
}
