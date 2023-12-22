package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

/**
 * Controller for handling comment requests
 */
@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    /**
     * Gets comments of an ad
     * @param id of an ad
     * @return ResponseEntity<Comments> consisting of Comments DTO(a list of comments and the size of the list) and Http status
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getCommentsForAd(@PathVariable int id) {
        return new ResponseEntity<>(commentService.getCommentsByAdId(id), HttpStatus.OK);
    }

    /**
     * Adds comment to an ad
     * @param id of an ad
     * @param commentDetails is CreateOrUpdateComment DTO consisting of a text of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<Comment> consisting of Comment DTO(author id, author image, author firstname, time when a comment was created, comment id and text of a comment) and Http status
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentToAd(@PathVariable int id,
                                  @RequestBody CreateOrUpdateComment commentDetails,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(commentService.addCommentToAd(id, commentDetails, userDetails), HttpStatus.OK);
    }

    /**
     * Deletes a comment
     * @param adId is id of an ad
     * @param commentId is id of a comment
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Http status
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                              @PathVariable int commentId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(adId, commentId, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Updates a comment
     * @param adId is id of an ad
     * @param commentId is id of a comment
     * @param commentDetails is CreateOrUpdateComment DTO consisting of a text of a comment
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<Comment> consisting of Comment DTO(author id, author image, author firstname, time when a comment was created, comment id and text of a comment) and Http status
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId,
                                 @PathVariable int commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(commentService.updateComment(adId, commentId, commentDetails, userDetails), HttpStatus.OK);
    }
}