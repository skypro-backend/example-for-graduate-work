package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
@Validated
public class CommentsController {
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария в объявлении",
            description = "Удаление комментария по id объявления и id комментария авторизованным пользователем")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        // Реализация удаления комментария в объявлений
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления",
            description = "Получение всех комментариев объявления по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentsDto> getCommentsByAd(@PathVariable("id") Integer adId) {
        CommentsDto allCommentsDtoList = new CommentsDto(0, new ArrayList<>());
        return ResponseEntity.ok(allCommentsDtoList);
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению",
            description = "Добавление комментария к объявлению по его id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable("id") Integer adId,
                                                     @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto newCommentDto = new CommentDto(1, "imagePath",
                "authorFirstName", (long) 100500, 1, createOrUpdateCommentDto.text());
        return ResponseEntity.ok(newCommentDto);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария",
            description = "Обновление комментария к объявлению по id объявления и id комментария")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentDto> updateCommentToAd(@PathVariable("adId") Integer adId,
                                                        @PathVariable("commentId") Integer commentId,
                                                        @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto updatedCommentDto = new CommentDto(1, "imagePath",
                "authorFirstName", (long) 100500, 1, createOrUpdateCommentDto.text());
        return ResponseEntity.ok(updatedCommentDto);
    }

}
