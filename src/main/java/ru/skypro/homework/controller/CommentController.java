package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Комментарии", description = "Управление комментариями объявлений")
public class CommentController {

    private final CommentService commentService;

    /**
     * Получение комментариев объявления
     */
    @Operation(summary = "Получение комментариев объявления", operationId = "getComments")
    @ApiResponse(responseCode = "200", description = "Успешное получение комментариев",
            content = @Content(schema = @Schema(implementation = Comment.class)))
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer adId) {

        return ResponseEntity.ok(commentService.getCommentsByAdId(adId));
    }

    /**
     * Добавление комментария (через SecurityContext)
     */
    @Operation(summary = "Добавление комментария", operationId = "addComment")
    @ApiResponse(responseCode = "201", description = "Комментарий создан",
            content = @Content(schema = @Schema(implementation = Comment.class)))
    @PostMapping
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer adId,
            @Valid @RequestBody CreateOrUpdateComment commentRequest,
            Authentication authentication) {
        Comment newComment = commentService.addComment(adId, commentRequest, authentication.getName());

        return ResponseEntity.status(201).body(newComment);
    }

    /**
     * Обновление комментария (проверка через @PreAuthorize в сервисе)
     */
    @Operation(summary = "Обновление комментария", operationId = "updateComment")
    @ApiResponse(responseCode = "200", description = "Комментарий обновлен",
            content = @Content(schema = @Schema(implementation = Comment.class)))
    @PatchMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @Valid @RequestBody CreateOrUpdateComment commentRequest) {

        Comment updatedComment = commentService.updateComment(adId, commentId, commentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Удаление комментария (проверка через @PreAuthorize в сервисе)
     */
    @Operation(summary = "Удаление комментария", operationId = "deleteComment")
    @ApiResponse(responseCode = "204", description = "Комментарий удален")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId) {

        commentService.deleteComment(adId, commentId);
        return ResponseEntity.noContent().build();
    }
}