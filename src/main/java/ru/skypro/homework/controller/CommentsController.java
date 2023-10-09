package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto> getAdComments(@PathVariable("id") Integer id) {
     return ResponseEntity.ok(new  CommentDto());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(new CommentDto());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("adId") Integer adId,
                                                    @PathVariable("commentId") Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(new CommentDto());
    }
}
