package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
public class CommentsController {
    // Когда у нас появятся сервисы: private final CommentService commentService;

    @GetMapping("/{id}/comments")
    public CommentDto getComments(@PathVariable int id) {
        return new CommentDto();
    }

//       В будущем можно работать, когда сервисы сделаем
//        @GetMapping("/{id}/comments")
//        public ResponseEntity<CommentsDto> getComments(@PathVariable int id) {
//            return ResponseEntity.ok(commentService.getComments(id));
//        }


    @PostMapping("/{id}/comments")
    public CommentDto addComment(@PathVariable int id,
                                 @RequestBody CreateOrUpdateCommentDto dto) {
        return new CommentDto();
    }

//        Для сервисов
//        @PostMapping("/{id}/comments")
//        public ResponseEntity<CommentDto> addComment(@PathVariable int id,
//                                                     @RequestBody CreateOrUpdateCommentDto dto) {
//            var body = commentService.create(id, dto);
//            return ResponseEntity.ok(body);
//        }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public CommentDto deleteComment(@PathVariable(name = "adId") int advertId,
                                    @PathVariable int commentId) {
        return new CommentDto();
    }

//        Для сервисов
//        @DeleteMapping("/{adId}/comments/{commentId}")
//        public ResponseEntity<?> deleteComment(@PathVariable(name = "adId") int advertId,
//                                               @PathVariable int commentId) {
//            commentService.delete(advertId, commentId);
//            return ResponseEntity.ok().build();
//        }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable(name = "adId") int advertId,
                                    @PathVariable int commentId,
                                    @RequestBody CreateOrUpdateCommentDto dto) {
        return new CommentDto();
    }

//        Для сервисов
//        @PatchMapping("/{adId}/comments/{commentId}")
//        public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "adId") int advertId,
//                                                        @PathVariable int commentId,
//                                                        @RequestBody CreateOrUpdateCommentDto dto) {
//            var body = commentService.update(advertId, commentId, dto);
//            return ResponseEntity.ok(body);
//        }
}

