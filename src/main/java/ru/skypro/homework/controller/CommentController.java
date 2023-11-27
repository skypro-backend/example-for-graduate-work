package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;
    @GetMapping("/{id}/getComments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comments());
    }


    @PostMapping("/{id}/addComment")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comment());
    }
    @PutMapping("/{adId}/updateComment/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                 @PathVariable Integer commentId) {
        return new ResponseEntity<>(new Comment(),HttpStatus.OK);
    }
    @DeleteMapping("/{adId}/deleteComment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
