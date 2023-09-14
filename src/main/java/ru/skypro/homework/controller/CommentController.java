package ru.skypro.homework.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateCommentRequest;
import ru.skypro.homework.dto.comment.UpdateCommentRequest;
import ru.skypro.homework.service.impl.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/ad/{adId}")
    public List<CommentDTO> getCommentsByAdId(@PathVariable Long adId) {
        try {
            return commentService.getCommentsByAdId(adId);
        } catch (Exception e) {
            logger.error("An error occurred while fetching comments for ad with ID: " + adId, e);
            throw e; // Rethrow the exception to let Spring handle it
        }
    }

    @PostMapping("/ad/{adId}")
    public CommentDTO addComment(@PathVariable Long adId, @RequestBody CreateCommentRequest createCommentRequest) {
        try {
            return commentService.addComment(adId, createCommentRequest);
        } catch (Exception e) {
            logger.error("An error occurred while adding a comment to ad with ID: " + adId, e);
            throw e;
        }
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("hasRole('USER') and @commentService.isCommentOwner(authentication, #commentId)")
    public CommentDTO updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        try {
            return commentService.updateComment(commentId, updateCommentRequest);
        } catch (Exception e) {
            logger.error("An error occurred while updating comment with ID: " + commentId, e);
            throw e;
        }
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('USER') and @commentService.isCommentOwner(authentication, #commentId)")
    public void deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting comment with ID: " + commentId, e);
            throw e;
        }
    }
}