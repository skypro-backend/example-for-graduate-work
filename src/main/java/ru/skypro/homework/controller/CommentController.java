package ru.skypro.homework.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class CommentController {

    @GetMapping("{id}/comments")
    public ResponseEntity<?> getComment() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<?> addComment() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment() {
        return ResponseEntity.ok().build();
    }
}
