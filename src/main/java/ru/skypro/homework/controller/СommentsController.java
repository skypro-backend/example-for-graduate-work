package ru.skypro.homework.controller;

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
    public Comments getComments(@PathVariable int id) {
        System.out.println("тут будет получение комментариев объявления ");
        return commentsService.getComments(id);
    }
    @PostMapping("/{id}/comments")
    public Comments addComment(@PathVariable int id, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        System.out.println("Добавление комментария к  объявлению");
        return commentsService.addComment(id);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        System.out.println("Удаление комментария ");

    }
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public CreateOrUpdateComment updateComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        System.out.println("Обновление комментария");
        return commentsService.updateComment(adId, commentId, createOrUpdateComment);
    }
}
