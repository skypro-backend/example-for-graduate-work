package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")
    public List<CommentDTO> getComments(@PathVariable("id") Integer adId) {
        logger.info("get all comments");
        return commentService.getComments(adId);
    }

    //Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public void addComment(@PathVariable("id") Integer adId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        logger.info("add new comment");
        commentService.addComment(adId, createOrUpdateComment);
    }

    //Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        logger.info("delete comment");
        commentService.deleteComment(adId,commentId);
    }

    //Обновление комментария
    @PutMapping("/{adId}/comments/{commentId}")
    public void updateComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        logger.info("update comment");
        commentService.updateComment(adId,commentId);
    }
}
