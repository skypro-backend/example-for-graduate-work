package ru.skypro.homework.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class СommentsController {
    private final CommentsService commentsService;

    public СommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        return ResponseEntity.ok(commentsService.getAllComments(id));
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comments> addComment(@PathVariable int id, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(commentsService.addComment(id, createOrUpdateComment)) ;
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        System.out.println("Удаление комментария ");

    }
    @PatchMapping("/{adId}/comments/{commentId}")
    public CreateOrUpdateComment updateComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        System.out.println("Обновление комментария");
        return commentsService.updateComment(adId, commentId, createOrUpdateComment);
    }
}
