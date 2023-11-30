package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
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
    @GetMapping("/{id}/comments")
    public Comments getCommentsForAd(@PathVariable Integer id) {
        System.out.print("asd");
        return commentService.getCommentsByAdId(id);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public Comment addCommentToAd(@PathVariable Integer id,
                                  @RequestBody CreateOrUpdateComment commentDetails,
                                  @AuthenticationPrincipal UserDetails userDetails) {

        return commentService.addCommentToAd(id, commentDetails, userDetails);
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long adId,
                                                @PathVariable Integer commentId,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.deleteComment(adId, commentId, userDetails);
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Long adId,
                                 @PathVariable Long commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails) {

        return new Comment(1, "", "Homer", Instant.now().toEpochMilli(), 1, "text");
    }
}