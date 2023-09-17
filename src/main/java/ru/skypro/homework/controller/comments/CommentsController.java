package ru.skypro.homework.controller.comments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.dto.comments.in.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.comments.CommentsService;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/ads")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getComments(@PathVariable Integer id) {
        log.info("Get comments with adId: {} ", id);
        CommentsDto commentsDto = commentsService.getComments(id);
        return ResponseEntity.ok(commentsDto);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Add comment with adId: {} and comment {} ", id, createOrUpdateCommentDto);
        CommentDto addComment = commentsService.addComment(id, createOrUpdateCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        log.info("Delete comment adId {} and commentId {} ", adId, commentId);
        commentsService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Update comment with adId {} and commentId {} and createOrUpdateDto {}", adId, commentId, createOrUpdateCommentDto);
        CommentDto updatedComment = commentsService.updateComment(adId, commentId, createOrUpdateCommentDto);
        return ResponseEntity.ok(updatedComment);
    }
}
