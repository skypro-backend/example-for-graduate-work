package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;

/**
 * Класс-контроллер для обработки запросов, связанных с комментариями объявлений.
 */
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Комментарии", description = "работа с коментариями")
public class CommentsController {
    @Operation (
              summary = "Получение комментариев объявления"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentDto> getAdComments(@PathVariable("id") Integer id) {
     return ResponseEntity.ok(new  CommentDto());
    }
    @Operation(
              summary = "Добавление комментария к объявлению "
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(new CommentDto());
    }
    @Operation(
              summary = "Удаление комментария "
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }
    @Operation(
              summary = "Обновление комментария "
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("adId") Integer adId,
                                                    @PathVariable("commentId") Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(new CommentDto());
    }
}
