package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;
    private final AdRepository adRepository;


    public CommentController(CommentService commentService, AdRepository adRepository) {
        this.commentService = commentService;
        this.adRepository = adRepository;
    }

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<Comments> getComments(@PathVariable(name = "id") Integer id) {
        if (commentService.getCommentsOfOneAd(id.intValue()) == null) {
            return ResponseEntity.ok().build();
        } else {
            Comments retrievedComments = commentService.getCommentsOfOneAd(id.intValue());
            return ResponseEntity.ok(retrievedComments);
        }
    }



    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<?> addComment(@RequestBody CreateOrUpdateComment createOrUpdateComment, @PathVariable(name = "id") Integer id) {
        if (adRepository.getReferenceById(id.intValue()) == null) {
            return ResponseEntity.notFound().build();
        } else {
            Comment addedComment = commentService.addCommentToAd(createOrUpdateComment, id.intValue());
            return ResponseEntity.ok(addedComment);
        }
    }


    @Transactional
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class)
                            )),
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<Comment> deleteComment(@PathVariable (name = "adId") Integer adId,
                                           @PathVariable(name = "commentId") Integer commentId, Authentication authentication) {
        boolean deleteOrNot = commentService.deleteCommentByIdAndAdId(adId, commentId, authentication.getName());
        if (deleteOrNot) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<?> updateComment(@PathVariable(name = "adId") Integer adId,
                                                 @PathVariable(name = "commentId") Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        if (commentService.patchCommentByIdAndAdId(adId, commentId, createOrUpdateComment,authentication.getName())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
