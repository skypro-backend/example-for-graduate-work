package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CommentsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;

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
    public ResponseEntity<CommentDTO> getComments(@PathVariable long id,
                                                  @RequestBody CommentsDTO commentsDTO) {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok(new CommentDTO());
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
    public ResponseEntity<CommentDTO> addComment(@PathVariable long id,
                                                 @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok(new CommentDTO());
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
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable long adId,
                                                    @PathVariable long commentId) {
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
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long adId,
                                                    @PathVariable long commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok(new CommentDTO());
    }
}