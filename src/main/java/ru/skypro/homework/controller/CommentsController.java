package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;


@Slf4j
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
                                                 @RequestBody CreateOrUpdateCommentDTO createCommentDTO) {
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
                                                    @RequestBody CreateOrUpdateCommentDTO updateCommentDTO) {
        return ResponseEntity.ok(new CommentDTO());
    }
}
