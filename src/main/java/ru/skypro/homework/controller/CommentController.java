package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;

@RestController("/ads")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(Authentication authentication,
                                                   @PathVariable int id) {
        CommentsDTO comments = commentService.getComments(authentication, id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(Authentication authentication,
                                                 @PathVariable int id,
                                                 @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        CommentDTO comment = commentService.addComment(authentication, id, createOrUpdateCommentDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(comment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(Authentication authentication,
                                                    @PathVariable int adId,
                                                    @PathVariable int commentId) {
        commentService.deleteComment(authentication, adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(Authentication authentication,
                                                    @PathVariable int adId,
                                                    @PathVariable int commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        CommentDTO comment = commentService.updateComment(authentication, adId, commentId, createOrUpdateCommentDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(comment);
    }
}
