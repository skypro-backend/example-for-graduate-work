package ru.skypro.homework.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.service.CommentService;

import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapper<CommentDto>> getComments(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ResponseWrapper.of(commentService.listCommentsAdById(id)));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("id") Long id,
                                                 @RequestBody CreateOrUpdateComment createComment) {

        Optional<CommentDto> optionalCommentDto = commentService.createComment(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), id, createComment);

        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Long adId,
                                           @PathVariable("commentId") Long commentId) {

        if (commentService.deleteById(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), adId, commentId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("adId") Long adId,
                                           @PathVariable("commentId") Long commentId,
                                           @RequestBody CreateOrUpdateComment updateComment) {

        Optional<CommentDto> optionalCommentDto = commentService.editComment(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), adId, commentId, updateComment);

        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

}
