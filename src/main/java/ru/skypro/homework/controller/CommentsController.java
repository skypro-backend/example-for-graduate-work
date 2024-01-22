package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentsServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {
    private final CommentsServiceImpl service;

    @GetMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    @Operation(summary = "Получение комментариев объявления", description = "getComments")
    public ResponseEntity<CommentsDto> getCommentsByAd(@Parameter(description = "ID объявления") @PathVariable long id) {
        return ResponseEntity.ok(service.getComments(id));
    }

    @PostMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    @Operation(summary = "Добавление комментария к объявлению", description = "addComment")
    public ResponseEntity<CommentDto> addCommentFromAd(@Parameter(description = "ID лбъявления") @PathVariable long id,
                                                       @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                       Authentication authentication) {
        return ResponseEntity.ok(service.addComment(id,createOrUpdateComment,authentication));
    }

    @DeleteMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    @Operation(summary = "Удаление комментария", description = "deleteComment")
    public ResponseEntity<?> deleteComment(@Parameter(description = "ID объявления") @PathVariable long adId,
                                           @Parameter(description = "ID комментария") @PathVariable long commentId,
                                           Authentication authentication){
        service.deleteComment(adId,commentId,authentication);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    @Operation(summary = "Обновление комментария", description = "updateComment")
    public ResponseEntity<CommentDto> updateComment(@Parameter(description = "ID объявления") @PathVariable long adId,
                                                    @Parameter(description = "ID комментария") @PathVariable long commentId,
                                                    @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                    Authentication authentication){
        CommentDto commentDto = service.updateComment(adId,commentId,createOrUpdateComment,authentication);
        return ResponseEntity.ok(commentDto);
    }
}