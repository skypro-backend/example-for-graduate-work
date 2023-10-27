package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateCommentDto;

import java.util.List;

@RestController
@RequestMapping(path = "ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    @GetMapping("/{id}/comments")
    public List<CommentsDto> getComments(@PathVariable(value = "id") Integer id) {
        return List.of(new CommentsDto[]{});
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable(value = "id") Integer id,
                                 @RequestBody CreateCommentDto comment) {
        return ResponseEntity.ok(new CommentDto());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "adId") Integer adId,
                                              @PathVariable(value = "commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "adId") Integer adId,
                                                    @PathVariable(value = "commentId") Integer commentId,
                                                    @RequestBody CreateCommentDto comment) {
        return ResponseEntity.ok(new CommentDto());
    }

}
