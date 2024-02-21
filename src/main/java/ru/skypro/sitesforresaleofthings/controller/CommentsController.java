package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер по работе с комментариями
 */
@RestController
@RequestMapping("ads")
@Tag(name = "Комментарии")
public class CommentsController {

    // здесь будут поля сервисов


    public CommentsController() {
    }

    // здесь будет конструктор с параметрами

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получение комментариев объявления"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<Collection<?>> getComments() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавление комментария к объявлению"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> addComment() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментария"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> deleteComment() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Обновление комментария"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> updateComment() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }
}