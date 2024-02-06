package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")

public class CommentController {

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")

    public ResponseEntity<CommentsDTO> getComments(@PathVariable("id") Integer id) {
        CommentsDTO commentsDTO = null;// получение комментариев объявления по id
        if (commentsDTO != null) {
            return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Добавление комментария к объявлению
    @PostMapping ("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDTO comment) {
        if (id != null && comment != null) { // проверка
            CommentDTO newCommentDTO = null; //  добавление комментария
            return new ResponseEntity<>(newCommentDTO, HttpStatus.CREATED);
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
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("adId") int adId,
                                                    @PathVariable("commentId") int commentId){
        CommentDTO updatedCommentDTO = new CommentDTO();
        return ResponseEntity.ok(updatedCommentDTO);

    }
}

