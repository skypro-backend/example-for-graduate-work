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
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Добавление, получение, обновление и удаление комментариев")
public class CommentsController {

    private final CommentsService commentService;

    @PostMapping("{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению",
            description = "Добавление комментария по идентификационному номеру объявления авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий добавлен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comments.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Comment> addComment(@PathVariable("id") int id,
                                               @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                               Authentication authentication) {
        Comment comment = commentService.addComment(id, createOrUpdateComment, authentication.getName());
        return new ResponseEntity<>(comment, HttpStatus.OK);
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
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") int adId,
                                           @PathVariable("commentId") int commentId,
                                           Authentication authentication) {
        commentService.deleteComment(adId, commentId, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<Comments> getComments(@PathVariable("id") int id,
                                                Authentication authentication) {
        Comments comments = commentService.getComments(id, authentication.getName());
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
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") int adId,
                                 @PathVariable("commentId") int commentId,
                                 @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                 Authentication authentication) {
        Comment comment = commentService.updateComment(adId, commentId, createOrUpdateComment, authentication.getName());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}