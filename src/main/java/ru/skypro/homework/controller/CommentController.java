package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;

@RestController
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии", description = "API для работы с комментариями к объявлениям")
public class CommentController {

    // ==================== 1. ПОЛУЧЕНИЕ КОММЕНТАРИЕВ ====================

    @GetMapping
    @Operation(
            summary = "Получение комментариев объявления",
            description = "Возвращает список всех комментариев к указанному объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Список комментариев получен",
                    content = @Content(schema = @Schema(implementation = CommentsDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<CommentsDto> getComments(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long adId
    ) {
        // Скелет: возвращаем пустой список
        return ResponseEntity.ok(new CommentsDto());
    }

    // ==================== 2. ДОБАВЛЕНИЕ КОММЕНТАРИЯ ====================

    @PostMapping
    @Operation(
            summary = "Добавление комментария к объявлению",
            description = "Добавляет новый комментарий к указанному объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Комментарий добавлен",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long adId,

            @Parameter(description = "Текст комментария", required = true)
            @RequestBody CreateOrUpdateCommentDto createComment
    ) {
        // Скелет: возвращаем пустой объект
        return ResponseEntity.ok(new CommentDto());
    }

    // ==================== 3. УДАЛЕНИЕ КОММЕНТАРИЯ (НОВЫЙ) ====================

    @DeleteMapping("/{commentId}")
    @Operation(
            summary = "Удаление комментария",
            description = "Удаляет комментарий по ID. Доступно только автору комментария или администратору"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Комментарий удалён"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden. Нет прав на удаление", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Комментарий или объявление не найдены", content = @Content)
    })
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long adId,

            @Parameter(description = "ID комментария", example = "45", required = true)
            @PathVariable Long commentId
    ) {
        // Скелет: возвращаем 200 OK
        return ResponseEntity.ok().build();
    }

    // ==================== 4. ОБНОВЛЕНИЕ КОММЕНТАРИЯ (НОВЫЙ) ====================

    @PatchMapping("/{commentId}")
    @Operation(
            summary = "Обновление комментария",
            description = "Обновляет текст комментария. Доступно только автору комментария"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Комментарий обновлён",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden. Нет прав на редактирование", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Комментарий или объявление не найдены", content = @Content)
    })
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long adId,

            @Parameter(description = "ID комментария", example = "45", required = true)
            @PathVariable Long commentId,

            @Parameter(description = "Обновлённый текст комментария", required = true)
            @RequestBody CreateOrUpdateCommentDto updateComment
    ) {
        // Скелет: возвращаем пустой объект
        return ResponseEntity.ok(new CommentDto());
    }
}