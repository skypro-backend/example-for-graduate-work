package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentsController {
    @GetMapping
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Integer adId) {
        return ResponseEntity.ok(new CommentsDTO());
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(new CommentDTO());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(new CommentDTO());
    }
}
