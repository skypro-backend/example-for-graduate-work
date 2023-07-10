package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    private final CommentsService commentsService;

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<Comments> getComments(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(commentsService.getCommentsById(id));
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<Comment> addComment(@PathVariable(name = "id") Integer id,
                                              @RequestBody CreateOrUpdateComment newComment) {
        return ResponseEntity.ok().body(commentsService.addComment(id, newComment));
    }

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
                                            @RequestBody CreateOrUpdateComment newComment) {
        return ResponseEntity.ok().body(commentsService.updateComment(adId, commentId, newComment));
    }
}
