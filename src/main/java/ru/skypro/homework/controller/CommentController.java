package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

@RestController
@RequestMapping("/comment")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class CommentController {
    @PostMapping
    public Comment comment (@RequestBody Comment comment) {
        return new Comment();
    }

    @GetMapping
    public Comments comments(@PathVariable long Id) {
        return new Comments();
    }
}
