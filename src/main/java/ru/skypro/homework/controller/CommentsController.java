package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

@RestController
public class CommentsController {
    @GetMapping("/ads/{adId}/comments")
    public Comments getComments(@PathVariable int adId) {
        return new Comments(); // Возвращает пустой объект Comments
    }

    @PostMapping("/ads/{adId}/comments")
    public Comment addComment(@PathVariable int adId, @RequestBody Comment comment) {
        return new Comment(); // Возвращает пустой объект Comment
    }
}
