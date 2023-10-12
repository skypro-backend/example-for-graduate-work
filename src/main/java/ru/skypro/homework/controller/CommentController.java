package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;



@Log4j2
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/ads")
public class CommentController {
    CommentService commentService;

    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> receivingAdComments(@PathVariable int id) {

        CommentsDTO commentsDTO = commentService.receivingAdComments(id);
        log.info("Comment received");

        return ResponseEntity.ok().body(commentsDTO);
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{adId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int adId, @RequestBody CreateOrUpdateCommentDTO text) {

        CommentDTO newCommentDto = commentService.addComment(adId, text);

        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(summary = "Удаление комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {

        commentService.deleteComment(adId, commentId);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId,
                                                    @PathVariable int commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO text) {

        CommentDTO commentDTO = commentService.updateComment(adId, commentId, text);

        return ResponseEntity.ok().body(commentDTO);
    }
}
