package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.entities.CommentEntity;
import ru.skypro.homework.service.entities.UserEntity;
import ru.skypro.homework.service.impl.CommentServiceImpl;
import ru.skypro.homework.service.repositories.CommentRepository;

import java.util.Optional;

@Log4j2
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/ads")
public class CommentController {

    CommentRepository commentRepository;

    CommentServiceImpl commentService;

    @Operation(summary = "Получение комментариев объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> receivingAdComments(@PathVariable int id) {

        log.info("Comment received");

        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id, @RequestBody String text) {

        CommentDTO commentDTO = new CommentDTO();

        return ResponseEntity.ok().body(commentDTO);
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
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId, @PathVariable int commentId) {

        CommentDTO commentDTO = new CommentDTO();
        return ResponseEntity.ok().body(commentDTO);
    }
}
