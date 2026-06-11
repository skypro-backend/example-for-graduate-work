package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    @Operation(summary = "Получение комментариев объявления", operationId = "getComments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentsDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<CommentsDto> getComments(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @PostMapping("/ads/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению", operationId = "addComment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable("id") Integer id,
            @Parameter(description = "Текст комментария", required = true)
            @RequestBody CreateOrUpdateCommentDto createComment,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                commentService.addComment(authentication.getName(), id, createComment));
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария", operationId = "deleteComment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer adId,
            @Parameter(description = "ID комментария", required = true)
            @PathVariable Integer commentId,
            Authentication authentication
    ) {
        commentService.deleteComment(authentication.getName(), adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария", operationId = "updateComment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer adId,
            @Parameter(description = "ID комментария", required = true)
            @PathVariable Integer commentId,
            @Parameter(description = "Обновлённый текст комментария", required = true)
            @RequestBody CreateOrUpdateCommentDto updateComment,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                commentService.updateComment(authentication.getName(), adId, commentId, updateComment));
    }
}
