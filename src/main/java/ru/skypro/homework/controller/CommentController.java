package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Добавить комментарий к объявлению", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int id, @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        return ResponseEntity.ok(commentService.createComment(id, createOrUpdateComment, authentication));

    }

    @Operation(summary = "Получить комментарии объявления", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentsDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable int id) {
        return ResponseEntity.ok(commentService.findByAd(id));
    }

    @Operation(summary = "Удалить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content})
    })
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId,
                                           @PathVariable int commentId,
                                           Authentication authentication) throws IOException {
        if (commentService.deleteComment(adId, commentId, authentication)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @Operation(summary = "Обновить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content})
    })
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateById(@PathVariable int adId,
                                 @PathVariable int commentId,
                                 @Valid @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                 Authentication authentication) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, createOrUpdateComment, authentication));
    }
}
