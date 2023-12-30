package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDTO> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getAllByCommentById(id), HttpStatus.OK);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> createComments(@PathVariable Long id,
                                                  @RequestBody CreateOrUpdateCommentDTO text) {
        if (text == null || id == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(commentService.createComment(text),HttpStatus.OK);
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComments(@PathVariable Long adId, @PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId,
                                                @PathVariable Long commentId,
                                                @RequestBody CreateOrUpdateCommentDTO text) {
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(commentService.updateComment(adId, commentId, text), HttpStatus.OK);
    }
}
