package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentsServiceImpl;

import java.nio.file.AccessDeniedException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    private final CommentsServiceImpl service;

    @GetMapping("{id}/comments")
    @Operation(summary = "Получение комментариев объявления", description = "getComments")
    public ResponseEntity<CommentsDTO> getCommentsByListing(@Parameter(description = "ID объявления") @PathVariable long id) {
        return ResponseEntity.ok(service.getComments(id));
    }

    @PostMapping("{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению", description = "addComment")
    public ResponseEntity<CommentDTO> addCommentFromListing(@Parameter(description = "ID объявления") @PathVariable long id,
                                                            @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                            Authentication authentication) {
        return ResponseEntity.ok(service.addComment(id, createOrUpdateComment, authentication));
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария", description = "deleteComment")
    public ResponseEntity<?> deleteComment(@Parameter(description = "ID объявления") @PathVariable long listingId,
                                           @Parameter(description = "ID комментария") @PathVariable long commentId,
                                           Authentication authentication) throws AccessDeniedException {
        service.deleteComment(listingId, commentId, authentication);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария", description = "updateComment")
    public ResponseEntity<CommentDTO> updateComment(@Parameter(description = "ID объявления") @PathVariable long listingId,
                                                    @Parameter(description = "ID комментария") @PathVariable long commentId,
                                                    @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                    Authentication authentication) throws AccessDeniedException {
        CommentDTO commentDTO = service.updateComment(listingId, commentId, createOrUpdateComment, authentication);
        return ResponseEntity.ok(commentDTO);
    }
}
