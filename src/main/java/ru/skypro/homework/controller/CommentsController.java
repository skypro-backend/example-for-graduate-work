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
//@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    CommentServiceImpl commentServiceImpl;

    public CommentsController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/{adId}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer adId) {
        return new ResponseEntity<>(commentServiceImpl.getComments(adId), HttpStatus.OK);
    }
    @PostMapping("/{adId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer adId,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        Comment commentOptional = commentServiceImpl.addComment(adId, createOrUpdateComment, userDetails);
        return new ResponseEntity<>(commentOptional,HttpStatus.OK);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        commentServiceImpl.deleteComment(adId, commentId, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                 @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(commentServiceImpl.updateComment(adId,commentId,createOrUpdateComment,userDetails),HttpStatus.OK);
    }

}