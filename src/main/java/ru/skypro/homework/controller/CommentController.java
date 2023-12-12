package ru.skypro.homework.controller;

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
    public Comments getCommentsForAd(@PathVariable int id) {
        return commentService.getCommentsByAdId(id);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public Comment addCommentToAd(@PathVariable int id,
                                  @RequestBody CreateOrUpdateComment commentDetails,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.addCommentToAd(id, commentDetails, userDetails);
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId,
                              @PathVariable int commentId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(adId, commentId, userDetails);
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment(@PathVariable int adId,
                                 @PathVariable int commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.updateComment(adId, commentId, commentDetails, userDetails);
    }
}