package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        return ResponseEntity.ok(commentsService.getAllComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id,
                                                 @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment
                                                 ) {
        return ResponseEntity.ok(commentsService.addComment(id, createOrUpdateComment));
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentsService.deleteComment(adId, commentId);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CommentDTO updateComment(@PathVariable int adId, @PathVariable int commentId,
                                    @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentsService.updateComment(adId, commentId, createOrUpdateComment);
    }
}
