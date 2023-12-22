package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

@RestController
@RequestMapping("/ads")
public class CommentsController {

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарии получены"


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                    )
            )

    })
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления", description = "getComments", tags = {"Комментарии"})
    public ResponseEntity<CommentDTO> getComments(@Parameter(description = "ID объявления") @PathVariable long id){
        return ResponseEntity.ok(new CommentDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарий добавлен"


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                    )
            )

    })
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению", description = "addComment", tags = {"Комментарии"})
    public ResponseEntity<CommentDTO> addComment(@Parameter(description = "ID лбъявления") @PathVariable long id,
                                           @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO){
        return ResponseEntity.ok(new CommentDTO());
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарий удален"


            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                    )
            )

    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария", description = "deleteComment", tags = {"Комментарии"})
    public ResponseEntity<Void> deleteComment(@Parameter(description = "ID объявления") @PathVariable long adId,
                                           @Parameter(description = "ID комментария") @PathVariable long commentId){
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Комментарий добавлено"


            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                    )
            )

    })
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария", description = "updateComment", tags = {"Комментарии"})
    public ResponseEntity<CommentDTO> updateComment(@Parameter(description = "ID объявления") @PathVariable long adId,
                                                    @Parameter(description = "ID комментария") @PathVariable long commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO){
        return ResponseEntity.ok(new CommentDTO());
    }




}
