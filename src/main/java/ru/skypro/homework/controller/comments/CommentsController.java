package ru.skypro.homework.controller.comments;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/ads")
public class CommentsController {

    private final CommentsService commentsService;

    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{adId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable int adId) {
        logger.info("Get comments with adId: {} ", adId);
        return ResponseEntity.ok(commentsService.getComments(adId));
    }

    @PostMapping("/{adId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable int adId,
                                                 @RequestBody CommentDto commentDto) {
        logger.info("Add comment with adId: {} and comment {} ", adId, commentDto);
        CommentDto addComment = commentsService.addComment(adId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentByAdIdAndCommentId(@PathVariable int adId,
                                                                @PathVariable int commentId) {
        logger.info("Delete comment adId {} and commentId {} ", adId, commentId);
        commentsService.deleteCommentByAdIdAndCommentId(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int adId,
                                                    @PathVariable int commentId,
                                                    @RequestBody CommentDto commentDto) {
        logger.info("Update comment with adId {} and commentId {} ", adId, commentId);
        CommentDto updatedComment = commentsService.updateComment(adId, commentId, commentDto);
        return ResponseEntity.ok(updatedComment);
    }
}
