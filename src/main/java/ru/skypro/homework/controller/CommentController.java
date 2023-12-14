package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.skypro.homework.dto.ads.comments.*;

@RestController
@RequestMapping("/ads")

public class CommentController {

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")

    public ResponseEntity<Comments> getComments(@PathVariable("id") Integer id) {
        Comments comments = null;// получение комментариев объявления по id
        if (comments != null) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Добавление комментария к объявлению
    @PostMapping ("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id,
                                              @RequestBody CreateOrUpdateComment comment) {
        if (id != null && comment != null) { // проверка
            Comment newComment = null; //  добавление комментария
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    // удаление комментария
@DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") int adId,
                                              @PathVariable("commentId") int commentId) {
// commentService.deleteComment(commentId);
    return ResponseEntity.ok().build();

    }

// Обновление комментария

    @PatchMapping ("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") int adId,
                                                 @PathVariable("commentId") int commentId){
        Comment updatedComment = new Comment();
        return ResponseEntity.ok(updatedComment);

        }
    }



