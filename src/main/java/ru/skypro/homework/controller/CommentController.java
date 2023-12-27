package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;


    @GetMapping("{id}/comments")
    public ResponseEntity<List<CommentsDTO>> getCommentById(@PathVariable Long id) {
        List<CommentsDTO> foundComments = commentService.getAllByCommentById(id).stream().map(commentMapper
                .convertToCommentsDTO(text)).collect(Collectors.toList());
        if (foundComments == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundComments);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Void> createComments(@PathVariable Long id, @RequestBody CreateOrUpdateCommentDTO text) {
        if (text == null || id == null) {
            return ResponseEntity.notFound().build();
        }
        return commentService.createComment(commentMapper.convertToComment(new CommentDTO()));
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComments(@PathVariable Long adId, @PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId,
                                                @PathVariable Long commentId,
                                                @RequestBody CreateOrUpdateCommentDTO text) {
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        return commentService.updateComment(commentMapper.convertToComment(CreateOrUpdateCommentDTO));
    }
}
