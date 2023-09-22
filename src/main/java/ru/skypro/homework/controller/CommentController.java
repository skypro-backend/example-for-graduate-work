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
@RequestMapping("/ads")
public class CommentController {

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<AdsDTO> receivingAdComments(@PathVariable int id) {

        AdsDTO adsDTO = new AdsDTO();

        return ResponseEntity.ok().body(adsDTO);
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id, @RequestBody String text) {

        CommentDTO commentDTO = new CommentDTO();

        return ResponseEntity.ok().body(commentDTO);
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId, @PathVariable int commentId){

        CommentDTO commentDTO = new CommentDTO();
        return ResponseEntity.ok().body(commentDTO);
    }
}
