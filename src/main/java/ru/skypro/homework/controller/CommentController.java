package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.ResponseWrapper;

import java.util.Collections;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Комментарии", description = "API для управления комментариями")
public class CommentController {

    @Operation(summary = "Получить все комментарии объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<ResponseWrapper<CommentDto>> getComments(@PathVariable Integer id) {
        log.info("Вызван метод getComments для объявления с id: {}", id);
        ResponseWrapper<CommentDto> wrapper = ResponseWrapper.<CommentDto>builder()
                .count(0)
                .results(Collections.emptyList())
                .build();
        return ResponseEntity.ok(wrapper);
    }

    @Operation(summary = "Создать комментарий к объявлению")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto commentDto) {
        log.info("Вызван метод addComment для объявления с id: {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDto());
    }

    @Operation(summary = "Удалить комментарий")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        log.info("Вызван метод deleteComment для объявления {} и комментария {}", adId, commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить комментарий")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto commentDto) {
        log.info("Вызван метод updateComment для объявления {} и комментария {}", adId, commentId);
        return ResponseEntity.ok(new CommentDto());
    }
}