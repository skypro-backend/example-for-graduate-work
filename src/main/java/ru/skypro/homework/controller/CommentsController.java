package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperCommentDTO;
import ru.skypro.homework.service.CommentsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@AllArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    // Получить комментарии объявления
    @GetMapping("/{id}/comments")
    ResponseEntity<ResponseWrapperCommentDTO> getAdComments(@PathVariable(name = "id") Long adId) {
        return ResponseEntity.ok(commentsService.getAdComments(adId));
    }

    // Добавить комментарий к объявлению
    @PostMapping("/{id}/comments")
    ResponseEntity<CommentDTO> addComment(@PathVariable(name = "id") Long adId,
                                 @RequestBody CommentDTO commentDTO) {


        return ResponseEntity.ok(commentsService.addComment(adId, commentDTO));
    }

    // Удалить комментарий
    @DeleteMapping("/{adId}/comments/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable(name = "adId") Long adId,
                                 @PathVariable(name = "commentId") Long commentId) {
        commentsService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    // Обновить комментарий
    @PatchMapping("/{adId}/comments/{commentId}")
    ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "adId") Long adId,
                                    @PathVariable(name = "commentId") Long commentId,
                                    @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentsService.updateComment(adId, commentId, commentDTO));
    }
}
