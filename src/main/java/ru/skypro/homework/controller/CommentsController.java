package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;

@RestController
@Slf4j
@Tag(name = "Комментарии")
@RequestMapping("/ads")
public class CommentsController {
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable("id") Long id){
        return ResponseEntity.ok(null);
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComments(@PathVariable("id") Long id ,String text ){
      return  ResponseEntity.ok(null);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Long adId,@PathVariable("commentId") Long commentId){
        return ResponseEntity.ok(null);
    }
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("adId") Long adId,@PathVariable("commentId") Long commentId){
        return ResponseEntity.ok(null);
    }
}
