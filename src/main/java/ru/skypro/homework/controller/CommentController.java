package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    private final CommentService commentService;


    @GetMapping
    @Operation(summary = "Получение комментариев объявления", description = "getComments", tags = {"Комментарии"})
    public ResponseEntity<CommentsDto> getComments (@PathVariable Integer adId) {
        try {
            CommentsDto comments = commentService.getComments(adId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Добавление комментария к объявлению", description = "ddComment", tags = {"Комментарии"})
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        try {
            CommentDto comment = commentService.addComment(adId, commentDto);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        try {
            commentService.deleteComment(adId, commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        try {
            CommentDto updatedComment = commentService.updateComment(adId, commentId, commentDto);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
