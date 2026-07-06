package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.Comments;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import ru.skypro.homework.dto.IdDto;
import ru.skypro.homework.service.ICommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии")
public class CommentController {

    private final ICommentService commentService;

    @GetMapping
    @Operation(summary = "Получение комментариев")
    public ResponseEntity<Comments> getComments(
            @PathVariable long adId,
            Pageable pageable) {
        return ResponseEntity.ok(commentService.getCommentsForAd(adId));
    }

    @PostMapping
    @Operation(summary = "Добавление комментария")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<IdDto> createComment(
            @PathVariable long adId,
            @RequestBody CreateOrUpdateComment data,
            Authentication auth) {
        Comment comment = commentService.createComment(adId, data, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdDto(comment.getPk()));
    }

    @PatchMapping("/{commentId}")
    @Operation(summary = "Редактирование комментария")
    @PreAuthorize("hasRole('ADMIN') or #commentId == authentication.principal.id")
    public ResponseEntity<Comment> updateComment(
            @PathVariable long adId,
            @PathVariable long commentId,
            @RequestBody CreateOrUpdateComment data,
            Authentication auth) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, data, auth));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удаление комментария")
    @PreAuthorize("hasRole('ADMIN') or #commentId == authentication.principal.id")
    public ResponseEntity<Void> deleteComment(
            @PathVariable long adId,
            @PathVariable long commentId,
            Authentication auth) {
        commentService.deleteComment(adId, commentId, auth);
        return ResponseEntity.noContent().build();
    }
}