package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    public ResponseEntity<CommentsDto> getCommentsByAd(@PathVariable Integer id) {
        return ResponseEntity.ok(new CommentsDto());
    }

    @PostMapping("{id}/comments") //+ 401 Unauthorized + 404 Not found
    public ResponseEntity<CommentDto> addCommentFromAd(@PathVariable Integer id, @RequestBody CreateOrUpdateComment text) {
        return ResponseEntity.ok(new CommentDto());
    }

    @DeleteMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<?> deleteAd(@PathVariable Integer adId,@PathVariable Integer commentId){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{adId}/comments/{commentId}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateComment text){
        return ResponseEntity.ok(new CommentDto());
    }
}