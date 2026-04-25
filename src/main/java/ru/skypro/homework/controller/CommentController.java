package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии", description = "API для работы с комментариями к объявлениям")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Получение комментариев объявления", description = "Возвращает список всех комментариев к указанному объявлению", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = Comments.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @GetMapping
    public ResponseEntity<Comments> getComments(@PathVariable("adId") Integer adId) {
        try {
            Comments comments = commentService.getComments(adId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Добавление комментария к объявлению", description = "Добавляет новый комментарий к объявлению", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrUpdateComment.class))), responses = {@ApiResponse(responseCode = "200", description = "Комментарий успешно добавлен", content = @Content(schema = @Schema(implementation = Comment.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addComment(@PathVariable("adId") Integer adId, @RequestBody @Valid CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Comment comment = new Comment();
        comment.setText(createOrUpdateComment.getText());
        Comment added = commentService.addComment(adId, comment, authentication.getName());
        return ResponseEntity.ok(added);
    }

    @Operation(summary = "Удаление комментария", description = "Удаляет комментарий (только для автора или администратора)", responses = {@ApiResponse(responseCode = "204", description = "Комментарий успешно удалён"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Комментарий не найден")})
    @DeleteMapping("/{commentId}")
    @PreAuthorize("@commentService.getComments(#adId).results.stream().anyMatch(c -> c.pk == #commentId && c.author == authentication.principal.id) || hasRole('ADMIN')")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId, Authentication authentication) {
        commentService.deleteComment(adId, commentId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновление комментария", description = "Изменяет текст комментария", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrUpdateComment.class))), responses = {@ApiResponse(responseCode = "200", description = "Комментарий успешно обновлён", content = @Content(schema = @Schema(implementation = Comment.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Комментарий не найден")})
    @PatchMapping(value = "/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@commentService.getComments(#adId).results.stream().anyMatch(c -> c.pk == #commentId && c.author == authentication.principal.id) || hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId, @RequestBody @Valid CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Comment comment = new Comment();
        comment.setText(createOrUpdateComment.getText());
        Comment updated = commentService.updateComment(adId, commentId, comment, authentication.getName());
        return ResponseEntity.ok(updated);
    }
}