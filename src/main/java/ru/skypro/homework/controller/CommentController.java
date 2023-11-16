package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Получение комментариев объявления
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsForAd(@PathVariable Long id) {
        List<Comment> comments = commentService.getCommentsForAd(id);
        return ResponseEntity.ok(comments);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentToAd(@PathVariable Long id,
                                                  @RequestBody CreateOrUpdateComment commentDetails) {
        Comment newComment = commentService.addCommentToAd(id, commentDetails);
        return ResponseEntity.ok(newComment);
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long adId, @PathVariable Long commentId) {
        if (commentService.deleteComment(adId, commentId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody CreateOrUpdateComment commentDetails) {
        Comment updatedComment = commentService.updateComment(adId, commentId, commentDetails);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}