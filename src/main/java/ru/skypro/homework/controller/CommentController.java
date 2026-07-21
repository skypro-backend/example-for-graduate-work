package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Получение комментариев объявления"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                commentService.getComments(id)
        );
    }

    @Operation(
            summary = "Добавление комментария"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateComment comment
    ) {

        return ResponseEntity.ok(
                commentService.addComment(id, comment)
        );
    }

    @Operation(
            summary = "Удаление комментария"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId
    ) {

        commentService.deleteComment(
                adId,
                commentId
        );

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление комментария"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @Valid @RequestBody CreateOrUpdateComment comment
    ) {
        return ResponseEntity.ok(
                commentService.updateComment(
                        adId,
                        commentId,
                        comment
                )
        );
    }
}