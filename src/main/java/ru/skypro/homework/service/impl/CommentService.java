package ru.skypro.homework.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateCommentRequest;
import ru.skypro.homework.dto.comment.UpdateCommentRequest;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getCommentsByAdId(Long adId) {
        try {
            List<Comment> comments = commentRepository.findByAdId(adId);
            return comments.stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An error occurred while getting comments by adId {}: {}", adId, e.getMessage());
            throw new RuntimeException("Failed to retrieve comments.", e);
        }
    }

    public CommentDTO addComment(Long adId, CreateCommentRequest createCommentRequest) {
        try {
            Comment comment = new Comment();
            comment.setId(adId);
            comment.setText(createCommentRequest.getText());

            comment = commentRepository.save(comment);

            return commentMapper.commentToCommentDTO(comment);
        } catch (Exception e) {
            logger.error("An error occurred while adding a comment: {}", e.getMessage());
            throw new RuntimeException("Failed to add a comment.", e);
        }
    }

    public CommentDTO updateComment(Long commentId, UpdateCommentRequest updateCommentRequest) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));

            comment.setText(updateCommentRequest.getText());

            comment = commentRepository.save(comment);

            return commentMapper.commentToCommentDTO(comment);
        } catch (IllegalArgumentException e) {
            logger.error("Comment not found with id: {}", commentId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while updating comment with id {}: {}", commentId, e.getMessage());
            throw new RuntimeException("Failed to update comment.", e);
        }
    }

    public void deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting comment with id {}: {}", commentId, e.getMessage());
            throw new RuntimeException("Failed to delete comment.", e);
        }
    }
    public boolean isCommentOwner(Authentication authentication, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        User currentUser = (User) authentication.getPrincipal();
        return comment != null && currentUser != null && comment.getUser().getId().equals(currentUser.getId());
    }
}
