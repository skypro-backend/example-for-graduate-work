package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

/**
 * Контроллер для работы с комментариями к объявлениям.
 * <p>
 * Обеспечивает полный цикл управления комментариями:
 * <ul>
 *   <li>Получение списка комментариев по ID объявления</li>
 *   <li>Добавление нового комментария</li>
 *   <li>Редактирование и удаление существующих комментариев</li>
 * </ul>
 * Все операции, кроме получения списка, требуют аутентификации.
 * Редактирование и удаление доступны только автору комментария или администратору.
 *
 * @see CommentService — основной сервис для бизнес-логики комментариев
 * @see Comments — DTO для возврата списка комментариев
 * @see Comment — DTO для отдельного комментария
 * @see CreateOrUpdateComment — DTO для создания и обновления комментария
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии", description = "API для работы с комментариями к объявлениям")
public class CommentController {

    /**
     * Сервис для выполнения операций с комментариями.
     */
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Получение всех комментариев к объявлению.
     * <p>
     * Доступно без авторизации. Возвращает список комментариев,
     * отсортированных по дате создания (сначала новые).
     *
     * @param adId идентификатор объявления
     * @return {@link ResponseEntity} с объектом {@link Comments}, содержащим список комментариев
     * @apiNote HTTP GET /ads/{id}/comments → 200 OK
     */
    @Operation(summary = "Получение комментариев объявления", description = "Возвращает список всех комментариев к указанному объявлению", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = Comments.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @GetMapping
    public ResponseEntity<Comments> getComments(@PathVariable("adId") Integer adId) {
        Comments comments = commentService.getComments(adId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Добавление нового комментария к объявлению.
     * <p>
     * Доступно только авторизованным пользователям. Принимает текст комментария
     * в формате JSON.
     *
     * @param adId                  идентификатор объявления
     * @param createOrUpdateComment DTO с текстом комментария
     * @param authentication        текущий аутентифицированный пользователь
     * @return {@link ResponseEntity} с добавленным объектом {@link Comment}
     * и статусом 200 при успехе
     */
    @Operation(summary = "Добавление комментария к объявлению", description = "Добавляет новый комментарий к объявлению", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrUpdateComment.class))), responses = {@ApiResponse(responseCode = "200", description = "Комментарий успешно добавлен", content = @Content(schema = @Schema(implementation = Comment.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addComment(@PathVariable("adId") Integer adId, @RequestBody @Valid CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {

        Comment added = commentService.addComment(adId, createOrUpdateComment, authentication.getName());
        return ResponseEntity.ok(added);
    }

    /**
     * Удаление комментария по его идентификатору.
     * <p>
     * Доступно только автору комментария или администратору.
     *
     * @param adId           идентификатор объявления
     * @param commentId      идентификатор комментария
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с пустым телом и статусом 200 при успехе,
     * 404 — если комментарий не найден,
     * 403 — если нет прав на удаление
     */
    @Operation(summary = "Удаление комментария", description = "Удаляет комментарий (только для автора или администратора)", responses = {@ApiResponse(responseCode = "200", description = "Комментарий успешно удалён"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Комментарий не найден")})
    @DeleteMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId, Authentication authentication) {
        commentService.deleteComment(adId, commentId, authentication.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Частичное обновление текста комментария.
     * <p>
     * Доступно только автору или администратору. Принимает новое значение поля {@code text}.
     *
     * @param adId                  идентификатор объявления
     * @param commentId             идентификатор комментария
     * @param createOrUpdateComment DTO с новым текстом комментария
     * @param authentication        текущий пользователь
     * @return {@link ResponseEntity} с обновлённым объектом {@link Comment},
     * 404 — если комментарий не найден,
     * 403 — если нет прав
     */
    @Operation(summary = "Обновление комментария", description = "Изменяет текст комментария", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrUpdateComment.class))), responses = {@ApiResponse(responseCode = "200", description = "Комментарий успешно обновлён", content = @Content(schema = @Schema(implementation = Comment.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Комментарий не найден")})
    @PatchMapping(value = "/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId, @RequestBody @Valid CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {

        Comment updated = commentService.updateComment(adId, commentId, createOrUpdateComment, authentication.getName());
        return ResponseEntity.ok(updated);
    }
}