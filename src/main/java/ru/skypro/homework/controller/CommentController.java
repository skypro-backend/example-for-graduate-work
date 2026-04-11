package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;

import jakarta.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "API для управления комментариями")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    @Operation(
            summary = "Получение комментариев объявления",
            description = "Возвращает список комментариев для указанного объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = Comments.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Comments> getComments(@PathVariable("id") Integer adId) {
        log.info("Getting comments for ad with id: {}", adId);
        Comments comments = commentService.getCommentsByAdId(adId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/ads/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Добавление комментария к объявлению",
            description = "Создает новый комментарий для указанного объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарий успешно добавлен",
                    content = @Content(schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Comment> addComment(
            @PathVariable("id") Integer adId,
            @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
            Authentication authentication) {

        log.info("Adding comment to ad {} by user: {}", adId, authentication.getName());

        return commentService.addComment(adId, createOrUpdateComment, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("isAuthenticated() and @commentService.isCommentOwner(#commentId, authentication.name)")
    @Operation(
            summary = "Удаление комментария",
            description = "Удаляет комментарий по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно удален"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    public ResponseEntity<Void> deleteComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            Authentication authentication) {

        log.info("Deleting comment {} by user: {}", commentId, authentication.getName());

        if (commentService.deleteComment(commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("isAuthenticated() and @commentService.isCommentOwner(#commentId, authentication.name)")
    @Operation(
            summary = "Обновление комментария",
            description = "Обновляет текст комментария по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарий успешно обновлен",
                    content = @Content(schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    public ResponseEntity<Comment> updateComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
            Authentication authentication) {

        log.info("Updating comment {} by user: {}", commentId, authentication.getName());

        return commentService.updateComment(commentId, createOrUpdateComment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}