package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

import java.util.Collections;

@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public ResponseEntity<CommentsDto> getComments(@PathVariable Integer id){
        return ResponseEntity.ok(new CommentsDto(0, Collections.emptyList()));
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto dto){
        return ResponseEntity.ok(new CommentDto(1, null, "Ivan",
                System.currentTimeMillis(), 1, dto.text()));
    }

    @DeleteMapping("/{adId}/comments/{commentsId}")
    @Operation(summary = "Удаление комментария")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comment/{commentId}")
    @Operation(summary = "Обновление комментария")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto dto){
        return ResponseEntity.ok(new CommentDto(1, null, "Ivan",
                System.currentTimeMillis(), commentId, dto.text()));
    }
}
