package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getCommentById(@PathVariable Long id) {
        List<Comment> foundComments = commentService.getAllByCommentById(id);
        if (foundComments == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundComments);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Void> createComments(@PathVariable Long id, @RequestBody CreateOrUpdateComment text) {
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        commentService.createComment(id, text);
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComments(@PathVariable Long adId, @PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId, @PathVariable Long commentId, @RequestBody CreateOrUpdateComment text) {
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        commentService.updateComment(adId, commentId, text);
    }
}
