package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.constant.WebConfigConstant;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Slf4j
@CrossOrigin(value = WebConfigConstant.CORS_ORIGIN)
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentsController {

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(new Comments());
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
                                              @RequestBody CreateOrUpdateComment comment) {
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment comment) {
        return ResponseEntity.ok(new Comment());
    }
}