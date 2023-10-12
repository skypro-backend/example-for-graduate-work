package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.projection.CommentView;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.service.comment.CommentService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    /**
     * Этот метод обрабатывает GET-запросы на получение комментариев объявления
     *
     * @param id идентификатор объявления
     * @return Возвращает список комметариев, оставленных к объявлению с указанным id
     */
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Comments.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Comments> getAllCommentsByAdId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.getAllCommentsByAdId(id));
    }

    /**
     * Этот метод обрабатывает POST-запросы на добавление комментария к объявлению
     *
     * @param id идентификатор объявления
     * @param comment комментарий
     * @param authentication данные авторизированного пользователя
     * @return Возвращает добавленный комментарий
     */
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<CommentView> createComment(@PathVariable("id") Integer id,
                                                     @RequestBody @Valid CreateOrUpdateComment comment,
                                                     Authentication authentication) {
        return ResponseEntity.ok(commentService.createComment(id, comment, authentication));
    }

    /**
     * Этот метод обрабатывает DELETE-запросы на удаление комментария к объявлению
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param authentication данные авторизированного пользователя
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
@PreAuthorize("hasRole('ADMIN') or @commentServiceImpl.findById(#commentId).getUserDTO().getEmail().equalsIgnoreCase(authentication.name)")
    public void deleteComment(@PathVariable("adId") Integer adId,
                              @PathVariable("commentId") Integer commentId,
                              Authentication authentication) {
        commentService.deleteComment(commentId, adId);
    }

    /**
     * Этот метод обрабатывает PATCH-запросы на обновление комментария к объявлению
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param comment комментарий
     * @param authentication данные авторизированного пользователя
     * @return Возвращает обновленный комментарий
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or @commentServiceImpl.findById(#commentId).getUserDTO().getEmail().equalsIgnoreCase(authentication.name)")
    public ResponseEntity<CommentView> updateComment(@PathVariable("adId") Integer adId,
                                                     @PathVariable("commentId") Integer commentId,
                                                     @RequestBody @Valid CreateOrUpdateComment comment,
                                                     Authentication authentication) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, comment));
    }
}

