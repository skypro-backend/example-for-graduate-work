package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    /**
     * Получение комментариев объявления.
     *
     * @param id - идентификатор объявления.
     * @return ResponseEntity.
     * Метод отправляет запрос на сервис в поисках объявления, если такое объявление
     * существует, то возвращает список комментариев к нему.
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comments());
    }

    /**
     * Добавление комментария к объявлению.
     *
     * @param id - идентификатор объявления.
     * @return ResponseEntity.
     * Метод отправляет запрос на сервис добавить новый комментарий к объявлению.
     */

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comment());
    }

    /**
     * Удаление комментария.
     *
     * @param adId      - идентификатор объявления.
     * @param commentId - идентификатор комментария.
     * @return ResponseEntity.
     * Метод отправляет запрос на сервис удалить конкретный комментарий к объявлению.
     */

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Обновление комментария.
     *
     * @param adId      - идентификатор объявления.
     * @param commentId - идентификатор комментария.
     * @return ResponseEntity.
     * Метод отправляет запрос на сервис изменить конкретный комментарий к объявлению.
     */

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comment());
    }
}