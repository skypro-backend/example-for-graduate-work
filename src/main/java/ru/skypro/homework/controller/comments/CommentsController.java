package ru.skypro.homework.controller.comments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.dto.comments.in.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.comments.CommentsService;

import javax.validation.Valid;

@Tag(name = "Comments")
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/ads")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Get comments of ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<CommentsDto> getComments(
            @Parameter(description = "Id of the ad to get comments", required = true)
            @PathVariable Integer id
    ) {
        CommentsDto commentsDto = commentsService.getComments(id);
        log.info("Got comments with adId: {} ", id);
        return ResponseEntity.ok(commentsDto);
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Add comment to ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "Id of the ad", required = true)
            @PathVariable Integer id,
            @Parameter(description = "DTO with a comment", required = true, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrUpdateCommentDto.class))})
            @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto addComment = commentsService.addComment(id, createOrUpdateCommentDto);
        log.info("Comment with adId: {} and comment {} added", id, createOrUpdateCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Delete comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Id of the ad", required = true)
            @PathVariable Integer adId,
            @Parameter(description = "Id of the comment", required = true)
            @PathVariable Integer commentId
    ) {
        commentsService.deleteComment(adId, commentId);
        log.info("Comment for ad with id {} and comment id {} deleted", adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Update comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "Id of the ad", required = true)
            @PathVariable Integer adId,
            @Parameter(description = "Id of the comment", required = true)
            @PathVariable Integer commentId,
            @Parameter(description = "DTO of the comment", required = true, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrUpdateCommentDto.class))})
            @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto
    ) {
        CommentDto updatedComment = commentsService.updateComment(adId, commentId, createOrUpdateCommentDto);
        log.info("Comment with ad id {} and comment id {} and createOrUpdateDto {} updated", adId, commentId, createOrUpdateCommentDto);
        return ResponseEntity.ok(updatedComment);
    }
}
