package ru.skypro.homework.controller;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
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
    public CommentDTO createComment(@PathVariable int id,@Valid @RequestBody CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
       return commentService.createComment(id, createOrUpdateComment, authentication);

    }

    @GetMapping("{id}/comments")
    public CommentsDTO getComments(@PathVariable int id) {
        return commentService.findByAd(id);
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public void deleteAd(@PathVariable int adId, @PathVariable int commentId, Authentication authentication) {
        commentService.deleteComment(adId, commentId, authentication);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public CommentDTO updateById(@PathVariable int adId,
                                 @PathVariable int commentId,
                                 @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                 Authentication authentication) {
        return commentService.updateComment(adId, commentId, createOrUpdateComment, authentication);
    }
}
