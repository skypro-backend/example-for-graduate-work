package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

@RestController
@Tag(name = "Комментарии")
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CommentsDto> getComments(@PathVariable int id) {
        return ResponseEntity.ok(new CommentsDto());
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CommentDto> addComment(
            @PathVariable int id,
            @RequestBody CreateOrUpdateCommentDto dto) {
        return ResponseEntity.ok(new CommentDto());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> deleteComment(@PathVariable int adId,
                                              @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable int adId,
            @PathVariable int commentId,
            @RequestBody CreateOrUpdateCommentDto dto) {
        return ResponseEntity.ok(new CommentDto());
    }
}