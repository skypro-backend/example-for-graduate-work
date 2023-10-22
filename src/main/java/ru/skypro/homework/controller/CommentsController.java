package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;

import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentsController {
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария в объявлении",
            description = "Удаление комментария по идентификационному номеру объявления и комментари авторизованным пользователем")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeComment(@PathVariable("adId") Long adId, @PathVariable("commentId") Long commentId) {
        // Реализация удаления комментария в объявлений
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получение комментариев объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getCommentsByAd(@PathVariable("id") Integer adId) {
        CommentsDto list = new CommentsDto(0, new ArrayList<>());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable("id") Integer adId,
                                                     @RequestBody CommentDto newCommentDto) {
        return ResponseEntity.ok(newCommentDto);
    }

    @Operation(summary = "Обновление комментария")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/{id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> updateCommentToAd(@PathVariable("id") Integer adId,
                                                        @PathVariable("comment_id") Integer commentId,
                                                        @RequestBody CommentDto updatedCommentDto) {
        return ResponseEntity.ok(updatedCommentDto);
    }

}
