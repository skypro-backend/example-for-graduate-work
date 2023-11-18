package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin("http://localhost:3000/")
public class CommentController {


    // Получение комментариев объявления
    @GetMapping("/{id}/comments")
    public Comments getCommentsForAd(@PathVariable Long id) {

        return new Comments(1, List.of(new Comment(1, "",
                "Homer", Instant.now().toEpochMilli(), 1, "TestComment")));
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public Comment addCommentToAd(@PathVariable Long id,
                                  @RequestBody CreateOrUpdateComment commentDetails) {

        return new Comment(1, "", "Homer", Instant.now().toEpochMilli(), 1, "text");
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long adId,
                                                @PathVariable Long commentId) {
        return ResponseEntity.ok().build();
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Long adId,
                                 @PathVariable Long commentId,
                                 @RequestBody CreateOrUpdateComment commentDetails) {

        return new Comment(1, "", "Homer", Instant.now().toEpochMilli(), 1, "text");
    }
}