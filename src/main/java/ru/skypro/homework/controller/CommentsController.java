package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Управление отзывами и комментариями на платформе Sopetov")
public class CommentsController {

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        return ResponseEntity.ok(new Comments());
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public ResponseEntity<Comment> addComment(@PathVariable int id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId,
                                              @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId,
                                                 @PathVariable int commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(new Comment());
    }
}