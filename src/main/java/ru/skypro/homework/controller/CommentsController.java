package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentsController {

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(new CommentsDTO());
    }

    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable(name = "id") int id,
                                                 @Valid @RequestBody CreateOrUpdateCommentDTO createCommentDTO) {
        return ResponseEntity.ok(new CommentDTO());
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "adId") int adId,
                                              @PathVariable(name = "commentId") int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "adId") int adId,
                                                    @PathVariable(name = "commentId") int commentId,
                                                    @Valid @RequestBody CreateOrUpdateCommentDTO updateCommentDTO) {
        return ResponseEntity.ok(new CommentDTO());
    }

}
