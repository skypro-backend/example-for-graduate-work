package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

@RestController
@RequestMapping("/ads")
public class СommentsController {
    private final CommentsService commentsService;

    public СommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}/comments")
    public Comments getFullCommets(@PathVariable int id) {
        System.out.println("тут будет получение комментариев объявления ");
        return commentsService.getFullComments(id);
    }
    @PostMapping("/{id}/comments")
    public Comments addComments(@PathVariable int id) {
        System.out.println("Добавление комментария к объявлению");
        return commentsService.addComments(id);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void removeComments(@PathVariable int id, @PathVariable int commentId) {
        System.out.println("Удаление комментария ");

    }
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public CreateOrUpdateComment updateComments(@PathVariable int id, @PathVariable int commentId) {
        System.out.println("Обновление комментария");
        return commentsService.updateComments(id, commentId);
    }
}
