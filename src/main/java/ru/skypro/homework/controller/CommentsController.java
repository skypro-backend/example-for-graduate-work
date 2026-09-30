package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get ad comments")
    @ApiResponse(responseCode = "200", description = "Comments returned")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                commentService.getComments(id)
        );
    }

    @Operation(summary = "Add comment")
    @ApiResponse(responseCode = "200", description = "Comment added")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateComment comment,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                commentService.addComment(
                        id,
                        authentication.getName(),
                        comment
                )
        );
    }

    @Operation(summary = "Delete comment")
    @ApiResponse(responseCode = "200", description = "Comment deleted")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Comment not found")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            Authentication authentication
    ) {
        commentService.deleteComment(
                adId,
                commentId,
                authentication.getName()
        );

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update comment")
    @ApiResponse(responseCode = "200", description = "Comment updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Comment not found")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateComment comment,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                commentService.updateComment(
                        adId,
                        commentId,
                        comment,
                        authentication.getName()
                )
        );
    }
}