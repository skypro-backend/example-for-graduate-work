package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class СommentController {

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "adId") Integer adId,
                                              @PathVariable(value = "commentId") Integer commentId){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария")
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "adId") Integer adId,
                                                 @PathVariable(value = "commentId") Integer commentId){
        return ResponseEntity.ok().build();
    }


}
