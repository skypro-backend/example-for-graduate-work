package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentsController {

    @GetMapping
    public ResponseEntity<CommentsDto> getComments(@PathVariable("adId") Integer id) {
        CommentsDto comments = new CommentsDto();
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(
            @PathVariable("adId") Integer adId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateComment) {
        CommentDto comment = new CommentDto();
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateComment) {
        CommentDto comment = new CommentDto();
        return ResponseEntity.ok(comment);
    }
}
