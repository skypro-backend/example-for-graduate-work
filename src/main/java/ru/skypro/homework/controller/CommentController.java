package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.impl.AdServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final AdServiceImpl adService;


    @GetMapping
    @Operation(summary = "Получение комментариев объявления", description = "getComments", tags = {"Комментарии"})
    public ResponseEntity<CommentsDto> getComments(@PathVariable("adId") Integer id,
                                                   Authentication authentication) {
        boolean commentsExist = commentService.getComments(id) != null;
        boolean isAuthenticated = authentication != null && authentication.getName() != null;

        if (commentsExist) {
            if (isAuthenticated) {
                return ResponseEntity.ok(commentService.getComments(id));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @Operation(summary = "Добавление комментария к объявлению", description = "ddComment", tags = {"Комментарии"})
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateCommentDto commentDto, Authentication authentication) {
        if (adService.findExtendedAd(adId) != null && commentDto != null) {
            return new ResponseEntity<>(commentService.createComment(adId, commentDto, authentication), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        try {
            commentService.deleteComment(adId, commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto commentDto,
                                                    Authentication authentication) {
        if (authentication.getName() != null) {
            return ResponseEntity.ok(commentService.updateComment(adId, commentId, commentDto));
        } else if (commentService.updateComment(adId, commentId, commentDto) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
