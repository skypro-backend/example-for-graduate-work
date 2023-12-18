package ru.skypro.homework.controller;

import liquibase.pro.packaged.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }
    //Получение комментариев объявления
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getCommentsForAd(@PathVariable int id) {
        return new ResponseEntity<>(commentService.getCommentsByAdId(id), HttpStatus.OK);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentToAd(@PathVariable int id,
                                  @RequestBody CreateOrUpdateComment commentDetails,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(commentService.addCommentToAd(id, commentDetails, userDetails), HttpStatus.OK);
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                              @PathVariable int commentId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(adId, commentId, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId,
                                 @PathVariable int commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(commentService.updateComment(adId, commentId, commentDetails, userDetails), HttpStatus.OK);
    }
}