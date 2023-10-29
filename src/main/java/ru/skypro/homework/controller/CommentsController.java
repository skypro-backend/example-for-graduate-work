package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
@RequiredArgsConstructor
@Validated
public class CommentsController {

    private final CommentService commentService;

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария в объявлении",
            description = "Удаление комментария по id объявления и id комментария авторизованным пользователем")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId, Authentication authentication) {
        {
            commentService.removeComment(adId, commentId, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления",
            description = "Получение всех комментариев объявления по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentsDto> getCommentsByAd(@PathVariable("id") Integer adId) {
        return ResponseEntity.ok(commentService.getCommentsByAd(adId));
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению",
            description = "Добавление комментария к объявлению по его id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable("id") Integer adId,
                                                     @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                     Authentication authentication) {
        return ResponseEntity.ok(commentService.addCommentToAd(adId, createOrUpdateCommentDto, authentication));
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария",
            description = "Обновление комментария к объявлению по id объявления и id комментария")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<CommentDto> updateCommentToAd(@PathVariable("adId") Integer adId,
                                                        @PathVariable("commentId") Integer commentId,
                                                        @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                        Authentication authentication) {
        return ResponseEntity.ok(commentService.updateCommentToAd(adId, commentId, createOrUpdateCommentDto, authentication));
    }


    /*
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }
    //todo нужно перенести в ExceptionHandlerClass

     */


}
