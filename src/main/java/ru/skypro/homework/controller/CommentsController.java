package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;
import java.util.List;
/**
 * Контроллер для работы с комментариями к объявлениям.
 * Позволяет получать, добавлять и удалять комментарии.
 */
@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Контроллер для работы с комментариями к объявлениям.
     * Позволяет получать, добавлять и удалять комментарии.
     */
    @GetMapping
    public List<Comment> getComments(@PathVariable Integer adId) {
        return commentService.getCommentsByAd(adId);
    }

    /**
     * Получает список комментариев для объявления.
     *
     * @param adId идентификатор объявления
     * @return список комментариев
     */
    @PostMapping
    public Comment addComment(@PathVariable Integer adId,
                              @RequestBody CreateOrUpdateComment dto,
                              Principal principal) {
        return commentService.addComment(adId, dto, principal.getName());
    }

    /**
     * Удаляет комментарий.
     * Удаление доступно владельцу или администратору.
     *
     * @param commentId идентификатор комментария
     * @param principal текущий пользователь
     */
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId,
                              Principal principal) {
        commentService.deleteComment(commentId, principal.getName());
    }
}