package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.*;

@RestController
@RequestMapping
public class CommentsController {

    @Operation(summary = "Получение комментариев объявления", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/ads/{id}/comments")
    public CommentsDto getComments(
            @Parameter(name = "id", required = true)
            @PathVariable int id) {
        return new CommentsDto();
    }

    @Operation(summary = "Добавление комментария к объявлению", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("ads/{id}/comments")
    public CommentDto addComment(
            @Parameter(name = "id", required = true)
            @PathVariable int id,
            @RequestBody CreateOrUpdateComment text) {
        return new CommentDto();
    }

    @Operation(summary = "Удаление комментария", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @Parameter(name = "adId", required = true)
            @PathVariable int adId,
            @Parameter(name = "commentId", required = true)
            @PathVariable int commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Обновление комментария", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public CommentDto updateComment(
            @Parameter(name = "adId", required = true)
            @PathVariable int adId,
            @Parameter(name = "commentId", required = true)
            @PathVariable int commentId,
            @RequestBody CreateOrUpdateComment text) {
        return new CommentDto();
    }
}
