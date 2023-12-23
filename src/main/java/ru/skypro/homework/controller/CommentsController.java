package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    CommentServiceImpl commentServiceImpl;

    public CommentsController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer adId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentServiceImpl.getComments(adId));
    }


    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer adId,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(commentServiceImpl.addComment(adId, createOrUpdateComment, userDetails));
    }


    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId,
                                              CustomUserDetails userDetails) {
        commentServiceImpl.deleteComment(adId, commentId, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                 CustomUserDetails userDetails) {
        return new ResponseEntity<>(commentServiceImpl.updateComment(adId,commentId,createOrUpdateComment,userDetails),HttpStatus.OK);
    }

}