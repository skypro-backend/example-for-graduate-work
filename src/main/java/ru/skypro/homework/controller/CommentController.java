package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentInfoDTO;
import ru.skypro.homework.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addCommentToAd(
            @PathVariable("id") Long pk,
            @RequestBody CommentDTO commentDTO
    ) {

        CommentDTO createdComment = commentService.addCommentToAd(pk, commentDTO);

        if (createdComment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Map<String, Object>> getCommentsByAdId(@PathVariable("id") Long pk) {
            List<CommentInfoDTO> comments = commentService.getAllCommentsByPK(pk);

            Map<String, Object> response = new HashMap<>();
            response.put("count", comments.size());
            response.put("results", comments);

            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByAdAndCommentId(@PathVariable("adId") Long pk, @PathVariable("commentId") Long commentId) {
        String result = commentService.deleteCommentByAdAndCommentId(pk, commentId);

        if ("Комментарий успешно удален".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PutMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentInfoDTO> updateCommentAndGetInfo(
            @PathVariable("adId") Long pk,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentInfoDTO updatedCommentInfo = commentService.updateCommentAndGetInfo(pk, commentId, commentDTO);

        if (updatedCommentInfo != null) {
            return ResponseEntity.ok(updatedCommentInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

