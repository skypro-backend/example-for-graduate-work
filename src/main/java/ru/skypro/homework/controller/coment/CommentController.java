package ru.skypro.homework.controller.coment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.data.dto.ListResponseDto;
import ru.skypro.homework.data.dto.comment.CommentItemDto;
import ru.skypro.homework.data.dto.comment.CreateOrUpdateCommentRequest;

@RestController
@RequestMapping("/ads")
public class CommentController {


    @GetMapping("/{adId}/comments")
    public ResponseEntity<ListResponseDto<CommentItemDto>> getComments(@PathVariable long adId) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{adId}/comments")
    public ResponseEntity<CommentItemDto> saveComment(@PathVariable long adId, @RequestBody CreateOrUpdateCommentRequest addCommentRequest) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable long adId, @PathVariable long commentId) {

    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentItemDto> updateComment(
            @PathVariable long adId,
            @PathVariable long commentId,
            @RequestBody CreateOrUpdateCommentRequest updateCommentRequest
    ) {
        return ResponseEntity.ok(null);
    }

}
