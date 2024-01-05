package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateCommentDTO;
import ru.skypro.kakavito.service.CommentService;

/**
 * Класс для управления потоком данных при работе с комментариями
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Запрос на получение списка комментариев к конкретному объявлению
     *
     * @param id
     * @see ru.skypro.kakavito.dto.CommentsDTO
     */
    @GetMapping("{id}/comments")
    public ResponseEntity<?> getCommentsById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getAllByCommentById(id));
    }

    /**
     * Запрос на создание комментария
     *
     * @param id
     * @param text
     * @return CommentDTO
     * @see CommentDTO
     */
    @PostMapping("{id}/comments")
    public ResponseEntity<?> createComments(@PathVariable int id,
                                            @RequestBody CreateOrUpdateCommentDTO text) {
        CommentDTO commentDTO = commentService.createComment(id, text);
        if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentDTO);
    }

    /**
     * Запрос на удаление комментария к объявлению
     *
     * @param adId
     * @param commentId
     */
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    /**
     * Запрос на изменение комментария
     *
     * @param adId
     * @param commentId
     * @param text
     * @return CommentDTO
     * @see CreateOrUpdateCommentDTO
     */
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int adId,
                                           @PathVariable int commentId,
                                           @RequestBody CreateOrUpdateCommentDTO text) {
        commentService.updateComment(adId, commentId, text);
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
