package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
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
    public CommentsDTO getComments(@PathVariable int id) {
        System.out.println("тут будет получение комментариев объявления ");
        return commentsService.getComments(id);
    }
    @PostMapping("/{id}/comments")
    public CommentsDTO addComment(@PathVariable int id, @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        System.out.println("Добавление комментария к  объявлению");
        return commentsService.addComment(id);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        System.out.println("Удаление комментария ");

    }
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public CreateOrUpdateCommentDTO updateComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        System.out.println("Обновление комментария");
        return commentsService.updateComment(adId, commentId, createOrUpdateCommentDTO);
    }
}
