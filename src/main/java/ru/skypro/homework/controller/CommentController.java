package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {
    private final AuthServiceImpl authService;
    private final CommentService commentService;

    public CommentController(AuthServiceImpl authService, CommentService commentService) {
        this.authService = authService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable("id") Integer id) {
        //todo добавить условие???? если role = admin то можно смотреть все комменты, юзер - только свои
        if (authService.getLogin() != null) {
            return ResponseEntity.ok(commentService.getComments(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        if (authService.getLogin() != null) {
            return ResponseEntity.ok(commentService.addComment(id, createOrUpdateComment, authService.getLogin().getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer commentId) {
        //
        //как сделать: нужно найти в БД юзера по его логину из authService.getLogin()
        //и проверить его статус: админ или юзер, если админ то удаляем коммент. если нет, то см. далее
        //Далее нужно найти коммент и проверить кому он принадлежит, если текущему юзеру (он не админ),
        //то удаляем, если нет, то статус 403.todo добавить условие - админ может удалять любые комменты, юзер - только свои.
        //Нужен ли adId ?
        if (authService.getLogin() != null) {
            String result = commentService.deleteComment(commentId, authService.getLogin().getUsername());
            if (result.equals("forbidden")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } else if (result.equals("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId,
                                                 @PathVariable("commentId") Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        //todo добавить условие, только пользователь написавший коммент может его править.
        //Нужен ли adId ?
        if (authService.getLogin() != null) {
            Comment comment = commentService.updateComment(commentId, createOrUpdateComment, authService.getLogin().getUsername());
            if (comment.getText().equals(createOrUpdateComment.getText())) {
                return ResponseEntity.ok(comment);
            } else if (comment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
