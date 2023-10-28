package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CommentsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import javax.validation.Valid;
import java.util.List;

/**
 * Класс-контроллер для обработки запросов, связанных с комментариями объявлений.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Комментарии", description = "работа с коментариями")
public class CommentsController {

    private final ImageService imageService;
    private final CommentService commentService;

    @Operation(summary = "Получение комментариев объявления", responses = {
              @ApiResponse (responseCode = "200", description = "OK", content = {
                        @Content (mediaType = "application/json", array = @ArraySchema (schema =
                        @Schema (implementation = CommentsDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto> getAdComments(@PathVariable("id") Integer id) {
        List <CommentDto> commentsDto = commentService.getAdComments(id);
        return ResponseEntity.ok((CommentDto) commentsDto);
    }

    @Operation(summary = "Добавление комментария к объявлению", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = CommentDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity <Comment> addCommentToAd(@PathVariable("id") Integer id,
                                                   @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                   Authentication authentication) {
        return ResponseEntity.ok(commentService.addCommentToAd(id, createOrUpdateCommentDto, authentication));
    }

    @Operation(summary = "Удаление комментария", responses = {
              @ApiResponse(responseCode = "200", description = "OK"),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Integer commentId,
                                              Authentication authentication) {
        commentService.deleteComment(adId, commentId, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = CommentDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity <Comment> updateComment(@PathVariable("adId") Integer adId,
                                                  @PathVariable("commentId") Integer commentId,
                                                  @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(commentService.updateComment (adId, commentId, createOrUpdateCommentDto));
    }
}
