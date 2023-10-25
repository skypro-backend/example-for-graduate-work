package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.*;
import lombok.experimental.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.adsDTO.*;
/**
 * Контроллер для работы с комментариями
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/ads")
public class CommentController {
    /**
     * Метод получения комментариев объявления
     */
    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<AdsDTO> receivingAdComments(@PathVariable int id) {
        AdsDTO adsDTO = new AdsDTO();
        return ResponseEntity.ok().body(adsDTO);
    }
    /**
     * Метод добавления комментария к объявлению
     */
    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id,
                                                 @RequestBody CreateCommentDTO text) {
        CommentDTO newCommentDTO = new CommentDTO();
        return ResponseEntity.ok().body(newCommentDTO);
    }
    /**
     * Метод удаления комментария к объявлению
     */
    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                                           @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }
    /**
     * Метод добавления комментария
     */
    @Operation(summary = "Обновление комментария")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId,
                                                    @PathVariable int commentId,
                                                    @RequestBody CreateCommentDTO text){
        CommentDTO commentDTO = new CommentDTO();
        return ResponseEntity.ok().body(commentDTO);
    }
}
