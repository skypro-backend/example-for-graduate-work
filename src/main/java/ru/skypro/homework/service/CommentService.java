package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.commentsToCommentDtos(comments);
    }

    public Optional<CommentDto> getCommentById(int commentId) {
        return commentRepository.findById(commentId)
                .map(commentMapper::commentToCommentDto);
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(savedComment);
    }

    public Optional<CommentDto> updateComment(int commentId, CommentDto commentDto) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);
        return existingComment.map(comment -> {
            Comment updatedComment = commentMapper.updateCommentFromDto(commentDto, comment);
            Comment savedComment = commentRepository.save(updatedComment);
            return commentMapper.commentToCommentDto(savedComment);
        });
    }

    public boolean deleteComment(int commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        } else {
            return false;
        }
    }
}
