package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateCommentDTO;
import ru.skypro.kakavito.service.CommentService;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("{id}/comments")
    public ResponseEntity<?> getCommentsById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getAllByCommentById(id));
//        return new ResponseEntity<>(commentService.getAllByCommentById(id), HttpStatus.OK);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<?> createComments(@PathVariable int id,
                                                     @RequestBody CreateOrUpdateCommentDTO text) {
        CommentDTO commentDTO = commentService.createComment(id, text);
        if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentDTO);
    }

//        if (text == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(commentService.createComment(text));
//        return new ResponseEntity<>(commentService.createComment(id, text),HttpStatus.OK);
//    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
//        Comment comment = commentService.findById(commentId);
//        if (comment == null) {
            return ResponseEntity.ok().build();
//        }
//        return commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int adId,
                                           @PathVariable int commentId,
                                           @RequestBody CreateOrUpdateCommentDTO text) {
        commentService.updateComment(adId, commentId, text);
        if (text == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
//                new ResponseEntity<>(commentService.updateComment(adId, commentId, text), HttpStatus.OK);
    }
}
