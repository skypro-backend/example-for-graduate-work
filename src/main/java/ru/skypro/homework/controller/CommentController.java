package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

/**
 * Контроллер для управления комментариями.
 * Предоставляет REST API для создания, получения, обновления и удаления комментариев к объявлениям.
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Получает список комментариев к объявлению.
     * Доступен без авторизации.
     *
     * @param adId ID объявления
     * @return ResponseEntity со списком комментариев
     */
    @GetMapping
    public ResponseEntity<Comments> getComments(@PathVariable Integer adId) {
        log.debug("Get comments for ad id: {}", adId);
        return ResponseEntity.ok(commentService.getComments(adId));
    }

    /**
     * Добавляет новый комментарий к объявлению.
     * Требуется авторизация с ролью USER или ADMIN.
     *
     * @param adId ID объявления
     * @param createOrUpdateComment текст комментария
     * @param authentication объект аутентификации
     * @return ResponseEntity с созданным комментарием
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer adId,
            @RequestBody CreateOrUpdateComment createOrUpdateComment,
            Authentication authentication) {
        log.debug("Add comment to ad id: {} by user: {}", adId, authentication.getName());
        Comment comment = commentService.addComment(adId, createOrUpdateComment, authentication.getName());
        return ResponseEntity.ok(comment);
    }

    /**
     * Удаляет комментарий по ID.
     * Требуется быть автором комментария или иметь роль ADMIN.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     * @param authentication объект аутентификации
     * @return ResponseEntity с кодом 200 OK
     */
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @commentService.isAuthor(authentication.name, #commentId)")
    public ResponseEntity<?> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            Authentication authentication) {
        log.debug("Delete comment id: {} from ad id: {} by user: {}",
                commentId, adId, authentication.getName());
        commentService.deleteComment(adId, commentId, authentication.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет текст комментария.
     * Требуется быть автором комментария или иметь роль ADMIN.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     * @param createOrUpdateComment обновленный текст
     * @param authentication объект аутентификации
     * @return ResponseEntity с обновленным комментарием
     */
    @PatchMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @commentService.isAuthor(authentication.name, #commentId)")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateComment createOrUpdateComment,
            Authentication authentication) {
        log.debug("Update comment id: {} from ad id: {} by user: {}",
                commentId, adId, authentication.getName());
        Comment comment = commentService.updateComment(adId, commentId, createOrUpdateComment,
                authentication.getName());
        return ResponseEntity.ok(comment);
    }
}