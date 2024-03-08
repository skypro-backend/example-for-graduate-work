package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

@RestController
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getAllComments(@PathVariable Integer id) {
        CommentsDto dto = new CommentsDto();
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto dto) {
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateCommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        CreateOrUpdateCommentDto dto = new CreateOrUpdateCommentDto();
        return ResponseEntity.ok(dto);
    }
}
