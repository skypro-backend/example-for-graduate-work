package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

import jakarta.validation.Valid;
import java.util.ArrayList;

@Slf4j
@CrossOrigin(value = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "API для управления комментариями")
public class CommentController {

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
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        Comments comments = new Comments();
        comments.setCount(0);
        comments.setResults(new ArrayList<>());
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/ads/{id}/comments")
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
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {

        Comment comment = new Comment();
        comment.setAuthor(1);
        comment.setAuthorImage("/images/default-avatar.jpg");
        comment.setAuthorFirstName("John");
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setPk(1);
        comment.setText(createOrUpdateComment.getText());
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
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
            @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
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
            @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {

        Comment comment = new Comment();
        comment.setAuthor(1);
        comment.setAuthorImage("/images/default-avatar.jpg");
        comment.setAuthorFirstName("John");
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setPk(commentId);
        comment.setText(createOrUpdateComment.getText());
        return ResponseEntity.ok(comment);
    }
}