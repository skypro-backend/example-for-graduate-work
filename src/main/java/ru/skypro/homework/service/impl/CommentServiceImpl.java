package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapping.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

/*    public CommentEntity createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public CommentEntity updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public Comment findComments(Integer id) {
        return commentRepository.findById(id);

    }
*/

}
