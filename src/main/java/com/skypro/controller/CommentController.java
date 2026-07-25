package com.skypro.controller;

import com.skypro.dto.Comment;
import com.skypro.dto.Comments;
import com.skypro.dto.CreateOrUpdateComment;
import com.skypro.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для управления комментариями к объявлениям.
 * <p>
 * Предоставляет эндпоинты для получения списка комментариев,
 * создания, редактирования и удаления комментариев.
 * </p>
 */
@RestController
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Возвращает список всех комментариев к указанному объявлению.
     * <p>
     * Требует авторизации.
     * </p>
     *
     * @param adId идентификатор объявления
     * @return объект {@link Comments} с количеством и списком комментариев
     */
    @Operation(summary = "Получение комментариев объявления", operationId = "getComments")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Comments.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{adId}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer adId) {
        return ResponseEntity.ok(commentService.getComments(adId));
    }

    /**
     * Добавляет новый комментарий к объявлению.
     * <p>
     * Требует авторизации.
     * </p>
     *
     * @param adId                   идентификатор объявления
     * @param createOrUpdateComment  текст комментария
     * @param authentication         объект аутентификации текущего пользователя
     * @return созданный комментарий в виде {@link Comment} со статусом 201 Created
     */
    @Operation(summary = "Добавление комментария к объявлению", operationId = "addComment")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Comment.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/{adId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer adId,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                              Authentication authentication) {
        Comment comment = commentService.addComment(adId, createOrUpdateComment, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /**
     * Удаляет комментарий по идентификатору.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId      идентификатор объявления (контекст)
     * @param commentId идентификатор комментария
     * @return статус 204 No Content
     */
    @Operation(summary = "Удаление комментария", operationId = "deleteComment")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Обновляет текст существующего комментария.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId                   идентификатор объявления (контекст)
     * @param commentId              идентификатор комментария
     * @param createOrUpdateComment  новый текст комментария
     * @return обновлённый комментарий в виде {@link Comment}
     */
    @Operation(summary = "Обновление комментария", operationId = "updateComment")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Comment.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, createOrUpdateComment));
    }
}