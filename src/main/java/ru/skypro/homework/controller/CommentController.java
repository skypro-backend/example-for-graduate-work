package ru.skypro.homework.controller;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("{id}/comments")
    public void createComment(@PathVariable int id,@Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        commentService.createComment(id, createOrUpdateComment);
    }

    @GetMapping("{id}/comments")
    public Collection<CommentDTO> getComments(@PathVariable int id) {
        return commentService.findByAd(id);
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public void deleteAd(@PathVariable int adId, @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public CommentDTO updateById(@PathVariable int adId, @PathVariable int commentId, @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentService.updateComment(adId, commentId, createOrUpdateComment);
    }
}
