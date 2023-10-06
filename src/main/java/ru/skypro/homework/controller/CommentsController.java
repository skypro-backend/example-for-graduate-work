package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Добавление, получение, обновление и удаление комментариев")
public class CommentsController {

    private final CommentsService commentService = null;

    @PostMapping("{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению",
            description = "Добавление комментария по идентификационному номеру объявления авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий добавлен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comments.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Comment> addComment(@PathVariable("id") Long id,
                                              @Valid @RequestBody CreateOrUpdateComment createComment) {
        Optional<Comment> optionalCommentDto = commentService.createComment(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), id, createComment);
        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария",
            description = "Удаление комментария по идентификационному номеру объявления и комментария " +
                    "авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий удален"),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Комментарий или объявление не найдены")
    })
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Long adId,
                                           @PathVariable("commentId") Long commentId) {
        if (commentService.deleteById(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), adId, commentId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("{id}/comments")
    @Operation(summary = "Получение комментариев объявления",
            description = "Получение комментариев по идентификационному номеру объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Ads.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Comments> getComments(@PathVariable("id") int id) {
        Comments comments = commentService.listCommentsAdById(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Operation(summary = "Обновление комментария",
            description = "Обновление комментария по идентификационному номеру объявления и комментария " +
                    "авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий обновлен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comments.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Комментарий или объявление не найдены")
    })
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Long adId,
                                                 @PathVariable("commentId") Long commentId,
                                                 @Valid @RequestBody CreateOrUpdateComment updateComment) {
        Optional<Comment> optionalCommentDto = commentService.editComment(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), adId, commentId, updateComment);

        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}