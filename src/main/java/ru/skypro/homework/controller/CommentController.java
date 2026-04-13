package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        log.info("Getting comments for ad with id: {}", id);
        Comments comments = commentService.getCommentsByAdId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(value = "/ads/{id}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addCommentJson(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateComment commentDto,
            Authentication authentication) {

        log.info("Adding comment to ad {} via JSON", id);
        log.info("Comment text: {}", commentDto.getText());

        return commentService.addComment(id, commentDto, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/ads/{id}/comments", consumes = MediaType.TEXT_PLAIN_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addCommentText(
            @PathVariable Integer id,
            @RequestBody String text,
            Authentication authentication) {

        log.info("Adding comment to ad {} via TEXT", id);
        log.info("Comment text: {}", text);

        CreateOrUpdateComment commentDto = new CreateOrUpdateComment();
        commentDto.setText(text);

        return commentService.addComment(id, commentDto, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/ads/{id}/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addCommentFormData(
            @PathVariable Integer id,
            @RequestParam("text") String text,
            Authentication authentication) {

        log.info("Adding comment to ad {} via FORM-DATA", id);
        log.info("Comment text: {}", text);

        CreateOrUpdateComment commentDto = new CreateOrUpdateComment();
        commentDto.setText(text);

        return commentService.addComment(id, commentDto, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/ads/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body,
            Authentication authentication) {

        log.info("Adding comment to ad {} via MAP", id);
        log.info("Request body: {}", body);

        String text = body.get("text");
        if (text == null) {
            text = body.get("comment");
        }
        if (text == null) {
            text = body.toString();
        }

        log.info("Extracted text: {}", text);

        CreateOrUpdateComment commentDto = new CreateOrUpdateComment();
        commentDto.setText(text);

        return commentService.addComment(id, commentDto, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("isAuthenticated() and @commentServiceImpl.isCommentOwner(#commentId, authentication.name)")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            Authentication authentication) {

        log.info("Deleting comment {} by user {}", commentId, authentication.getName());

        if (commentService.deleteComment(commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("isAuthenticated() and @commentServiceImpl.isCommentOwner(#commentId, authentication.name)")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateComment commentDto,
            Authentication authentication) {

        log.info("Updating comment {} by user {}", commentId, authentication.getName());

        return commentService.updateComment(commentId, commentDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}