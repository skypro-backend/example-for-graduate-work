package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDTO> getCommentsByListing(@PathVariable Integer id) {
        return ResponseEntity.ok(new CommentsDTO());
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDTO> addCommentFromListing(@PathVariable Integer id, @RequestBody CreateOrUpdateComment text) {
        return ResponseEntity.ok(new CommentDTO());
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteListing(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateComment text) {
        return ResponseEntity.ok(new CommentDTO());
    }
}
