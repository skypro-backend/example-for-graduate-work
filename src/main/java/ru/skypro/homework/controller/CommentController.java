package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<CommentsDto> getComments (@PathVariable int adId) {
        try {
            CommentsDto comments = commentService.getComments(adId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable int adId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        try {
            CommentDto comment = commentService.addComment(adId, commentDto);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        try {
            commentService.deleteComment(adId, commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        try {
            CommentDto updatedComment = commentService.updateComment(adId, commentId, commentDto);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
