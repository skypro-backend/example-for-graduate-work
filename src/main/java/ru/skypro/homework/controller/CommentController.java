package ru.skypro.homework.controller;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.service.CommentService;
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public void createComment(CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
    }

}
