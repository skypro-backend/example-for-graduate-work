package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.CommentService;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Комментарии", description = "API для управления комментариями")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    @Operation(summary = "Получить все комментарии объявления")
    public ResponseEntity<ResponseWrapper<CommentDto>> getComments(@PathVariable Integer id) {
        var comments = commentService.getCommentsByAdId(id);
        return ResponseEntity.ok(new ResponseWrapper<>(comments.size(), comments));
    }

    @PostMapping("/ads/{id}/comments")
    @Operation(summary = "Создать комментарий к объявлению")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto commentDto,
                                                 Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        CommentDto created = commentService.createComment(id, commentDto, userDetails.getUserEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Удалить комментарий")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Обновить комментарий")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, commentDto));
    }
}