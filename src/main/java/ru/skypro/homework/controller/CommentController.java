package ru.skypro.homework.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.Comments;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import ru.skypro.homework.dto.IdDto;


@RestController
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии")
public class CommentController {

    @GetMapping("")
    @Operation(summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    public ResponseEntity<Comments> getCommentsForAd(
            @PathVariable Integer adId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(new Comments());
    }

    @PostMapping("")
    @Operation(summary = "Добавление комментария к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    public ResponseEntity<IdDto> createComment(
            @PathVariable Integer adId,
            @RequestBody CreateOrUpdateComment request
    ) {
        Integer commentId = 1;
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdDto(commentId));
    }

    @PatchMapping("/{commentId}")
    @Operation(summary = "Обновление комментария",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    public ResponseEntity<Comment> updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateComment request
    ) {

        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удаление комментария",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId
    ) {

        return ResponseEntity.ok().build();
    }
}