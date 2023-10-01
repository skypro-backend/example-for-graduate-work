package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;

@RestController
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto> getAdComments(@PathVariable("id") Integer adId) {
     return new ResponseEntity<>(new CommentDto(), HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addCommentToAd(@PathVariable("id") Integer adId, @RequestBody CreateOrUpdateCommentDto createOrUpdareCommentDto) {
        return new ResponseEntity<>(new CommentDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        return new ResponseEntity<>(new CommentDto(), HttpStatus.OK);
    }
}
