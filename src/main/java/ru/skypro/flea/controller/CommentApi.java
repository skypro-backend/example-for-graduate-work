package ru.skypro.flea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.skypro.flea.dto.CommentDto;
import ru.skypro.flea.dto.CommentsDto;
import ru.skypro.flea.dto.CreateOrUpdateCommentDto;

import javax.validation.Valid;

public interface CommentApi {

    @Operation(summary = "Get ad's comments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CommentsDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @RequestMapping(value = "/ads/{id}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<CommentsDto> getComments(@PathVariable(name = "id") int id);

    @Operation(summary = "Add comment to the ad")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CommentDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @RequestMapping(value = "/ads/{id}/comments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<CommentDto> addComment(@PathVariable(name = "id") int id,
                                          @RequestBody @Valid CreateOrUpdateCommentDto comment);

    @Operation(summary = "Delete comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @RequestMapping(value = "/ads/{adId}/comments/{commentId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteComment(@PathVariable(name = "adId") int adId,
                                       @PathVariable(name = "commentId") int commentId);

    @Operation(summary = "Update the comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CommentDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @RequestMapping(value = "/ads/{adId}/comments/{commentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH)
    ResponseEntity<CommentDto> updateComment(@PathVariable(name = "adId") int adId,
                                             @PathVariable(name = "commentId") int commentId,
                                             @RequestBody @Valid CreateOrUpdateCommentDto comment);

}
