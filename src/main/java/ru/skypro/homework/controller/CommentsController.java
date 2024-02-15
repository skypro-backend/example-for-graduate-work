package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentsService;

    @GetMapping("/{ad_id}")
    public ResponseEntity<List<Comment>> getAllCommentsByAdId(@PathVariable("ad_id") Long adId) {
        return ResponseEntity.ok(commentsService.getAllCommentsByAdId(adId));
    }

    @PostMapping("/{ad_id}")
    public ResponseEntity<Comment> createComment(@PathVariable("ad_id") Long adId, @RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsService.createComment(adId, comment));
    }

    @PutMapping("/{ad_id}/{comment_id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("ad_id") Long adId, @PathVariable("comment_id") Long commentId, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentsService.updateComment(adId, commentId, comment));
    }

    @DeleteMapping("/{ad_id}/{comment_id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("ad_id") Long adId, @PathVariable("comment_id") Long commentId) {
        commentsService.deleteComment(adId, commentId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
