package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.time.Instant;

@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    //Получение комментариев объявления
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/comments")
    public Comments getCommentsForAd(@PathVariable Integer id) {
        return commentService.getCommentsByAdId(id);
    }

    // Добавление комментария к объявлению
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}/comments")
    public Comment addCommentToAd(@PathVariable Integer id,
                                  @RequestBody CreateOrUpdateComment commentDetails,
                                  @AuthenticationPrincipal UserDetails userDetails) {

        return commentService.addCommentToAd(id, commentDetails, userDetails);
    }

    // Удаление комментария
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer adId,
                                                @PathVariable Integer commentId,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        if (commentService.deleteComment(adId, commentId, userDetails)){
            return new ResponseEntity<>("Комментарий удален", HttpStatus.OK);
        } else return new ResponseEntity<>("Комментарий не найден", HttpStatus.BAD_REQUEST);
    }

    // Обновление комментария
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Integer adId,
                                 @PathVariable Integer commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails,
                                 @AuthenticationPrincipal UserDetails userDetails) {

        return commentService.updateComment(adId, commentId, commentDetails, userDetails);
    }
}