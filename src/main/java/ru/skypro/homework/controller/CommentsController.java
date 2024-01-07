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

    @GetMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    public ResponseEntity<Comments> getCommentsByAd(@PathVariable Integer id) {
        return ResponseEntity.ok(new Comments());
    }

    @PostMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    public ResponseEntity<Comment> addCommentFromAd(@PathVariable Integer id, @RequestBody CreateOrUpdateComment text) {
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<?> deleteAd(@PathVariable Integer adId,@PathVariable Integer commentId){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,@PathVariable Integer commentId,@RequestBody CreateOrUpdateComment text){
        return ResponseEntity.ok(new Comment());
    }
}