package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;

import javax.transaction.Transactional;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
@RequestMapping("/ads")
public class CommentController {

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<AdsDTO> receivingAdComments(@PathVariable int id) {

        if (!isNotFound()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AdsDTO adsDTO = new AdsDTO();

        return ResponseEntity.ok().body(adsDTO);
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id, @RequestBody String text) {

        if (!isNotFound()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CommentDTO commentDTO = new CommentDTO();

        return ResponseEntity.ok().body(commentDTO);
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {

        if (!isNotFound()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!isUserAuthorized()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId, @PathVariable int commentId){

        if (!isNotFound()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!isUserAuthorized()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CommentDTO commentDTO = new CommentDTO();
        return ResponseEntity.ok().body(commentDTO);
    }

    // 401 Unauthorized (Неавторизован):
    private boolean isUserAuthenticated() {
        //Проверки аутентификации пользователя
        return true;
    }

    // 403 Forbidden (Запрещено):
    private boolean isUserAuthorized() {
        // Ппроверка авторизации пользователя
        return true;
    }

    // 404 Forbidden (Не найден):
    private boolean isNotFound() {
        return true;
    }
}
