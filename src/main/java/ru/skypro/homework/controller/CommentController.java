package ru.skypro.homework.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;
    private final AdService adService;

    public CommentController(CommentService commentService, AdService adService) {
        this.commentService = commentService;
        this.adService = adService;
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        if (commentService.getCommentsOfOneAd(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            Comments retrievedComments = commentService.getCommentsOfOneAd(id);
            return ResponseEntity.ok(retrievedComments);
        }
    }



    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@RequestBody CreateOrUpdateComment createOrUpdateComment, @PathVariable Integer id) {
        if (adService.callAdById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            Comment addedComment = commentService.addCommentToAd(createOrUpdateComment, id);
            return ResponseEntity.ok(addedComment);
        }
    }



    @Transactional
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 Authentication authentication) {
        boolean choiceDelete = commentService.deleteCommentByIdAndAdId(adId, commentId, authentication.getName());
        if (choiceDelete) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable(name = "adId") Integer adId,
                                                 @PathVariable(name = "commentId") Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                 Authentication authentication) {
        if (commentService.patchCommentByIdAndAdId(adId, commentId, createOrUpdateComment, authentication.getName())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
