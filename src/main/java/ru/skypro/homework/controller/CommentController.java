package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.service.CommentService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ads/{adId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable long adId) {
        return null;
    }

    @PostMapping("/ads/{adId}/comments")
    public ResponseEntity<CommentDTO> postComment(@PathVariable long adId,
                                                  @RequestBody CommentDTO comment) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new CommentDTO());
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable long adId,
                                              @PathVariable long commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long adId,
                                                    @PathVariable long commentId,
                                                    @RequestBody CommentDTO comment) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new CommentDTO());
    }
}
