package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseHandler;
import ru.skypro.homework.service.CommentService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Добавление нового комментария", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("id") Integer id,
                                             @RequestBody CommentDto commentDto,
                                             Authentication authentication) {
        return ResponseEntity.ok(commentService.create(id, commentDto, authentication));
    }

    @Operation(
            summary = "Обновить комментарий", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PreAuthorize("@commentServiceImpl.getCommentById(#commentId).getAuthor().username" +
            "== authentication.name or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable("adId") Integer adId,
                                             @PathVariable("commentId") Integer commentId,
                                             @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.update(adId, commentId, commentDto));
    }

    @Operation(summary = "Получить комментарии", tags = "Комментарии")
    @GetMapping("/{id}/comments")
    public ResponseHandler<CommentDto> get(@PathVariable("id") Integer id) {
        return ResponseHandler.of(commentService.get(id));
    }

    @Operation(
            summary = "Удалить комментарий", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PreAuthorize("@commentServiceImpl.getCommentById(#commentId).getAuthor().username" +
            "== authentication.name or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> remove(@PathVariable("adId") Integer adId,
                                    @PathVariable("commentId") Integer commentId) {
        commentService.remove(adId, commentId);
        return ResponseEntity.ok().build();
    }

}
