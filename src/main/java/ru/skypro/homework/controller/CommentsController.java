package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.impl.CommentsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    private final CommentsService commentsService;

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<CommentsDto> getComments(@PathVariable(name = "id") Integer AdId) {
        return ResponseEntity.ok().body(commentsService.getCommentsById(AdId));
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<CommentDto> addComment(@PathVariable(name = "id") Integer AdId,
                                                 @RequestBody CreateOrUpdateCommentDto newComment) {
        return ResponseEntity.ok().body(commentsService.addComment(AdId, newComment));
    }
    @Secured({"ADMIN", "USER"})
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId) {
        commentsService.deleteComment(adId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PatchMapping("{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<?> updateComments(@PathVariable Integer adId,
                                            @PathVariable Integer commentId,
                                            @RequestBody CreateOrUpdateCommentDto newComment) {
        return ResponseEntity.ok().body(commentsService.updateComment(adId, commentId, newComment));
    }
}
