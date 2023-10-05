package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

//    private CommentsService = commentsService;

    /**
     * @param id - идентификатор объявления
     * @return ResponseEntity
     * Метод отправляет запрос на сервис в поисках объявления, если такое объявление
     * существует, то возвращает список комментариев к нему
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByAdId(@PathVariable(value = "id") long id) {
        List<Comment> comments = new ArrayList<>();
//        comments = commentsService.getCommentsByAdId(id);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(comments);
    }

    /**
     * @param id - идентификатор объявления
     * @return ResponseEntity
     * Метод отправляет запрос на сервис добавить новый комментарий к объявлению
     */

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentsByAdId(@PathVariable(value = "id") long id,
                                                           @RequestBody Comment comment) {
//        return commentsService.addCommentsByAdId(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * @param adId - идентификатор объявления
     * @param commentId - идентификатор комментария
     * @return ResponseEntity
     * Метод отправляет запрос на сервис удалить конкретный комментарий к объявлению
     */

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> delCommentsByAdId(@PathVariable(value = "adId") long adId,
                                                     @PathVariable(value = "commentId") long commentId) {
        //...
        return ResponseEntity.ok().build();
    }

    /**
     * @param adId - идентификатор объявления
     * @param commentId - идентификатор комментария
     * @return ResponseEntity
     * Метод отправляет запрос на сервис изменить конкретный комментарий к объявлению
     */

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> editCommentsByAdId(@PathVariable(value = "adId") long adId,
                                                @PathVariable(value = "commentId") long commentId) {

        //...

        return ResponseEntity.ok("comment was updated");
    }
}
