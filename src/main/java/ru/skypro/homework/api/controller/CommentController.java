package ru.skypro.homework.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.api.dto.Comment;
import ru.skypro.homework.api.dto.Comments;
import ru.skypro.homework.api.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Получение комментариев объявления", operationId = "getComments", tags = "Комментарии")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Comments.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @Operation(summary = "Добавление комментария к объявлению", operationId = "addComment", tags = "Комментарии")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
                                               @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                       content = @Content(schema = @Schema(implementation = CreateOrUpdateComment.class)))
                                               @Valid @RequestBody CreateOrUpdateComment comment,
                                               Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(authentication.getName(), id, comment));
    }

    @Operation(summary = "Удаление комментария", operationId = "deleteComment", tags = "Комментарии")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                               @PathVariable Integer commentId,
                                               Authentication authentication) {
        commentService.deleteComment(authentication.getName(), adId, commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария", operationId = "updateComment", tags = "Комментарии")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                  @PathVariable Integer commentId,
                                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                          content = @Content(schema = @Schema(implementation = CreateOrUpdateComment.class)))
                                                  @Valid @RequestBody CreateOrUpdateComment comment,
                                                  Authentication authentication) {
        return ResponseEntity.ok(commentService.updateComment(authentication.getName(), adId, commentId, comment));
    }
}
