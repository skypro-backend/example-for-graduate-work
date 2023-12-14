package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    /* method that will return list
    of comments to the specified ad*/
    @Operation(
            summary = "Getting all comments to the advertisement.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Method gets the list of comments to the advertisement."
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment list was successfully gotten.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Advertisement with specified id was not found."
                    )
            },
            tags = "Comments"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<Collection<?>> getComments(@Valid @Parameter(description = "Ad id") @PathVariable Integer id) {
        return ResponseEntity.status(200).build();
    }

    /* method that will add new
           comment to the specified ad*/
    @Operation(
            summary = "Adding new comment to advertisement.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Method adds a new comment to the advertisement."
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment was successfully added.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Advertisement with specified id was not found."
                    )
            },
            tags = "Comments"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@Valid @RequestBody CreateOrUpdateComment createOrUpdateComment, @Parameter(description = "Ad id") @PathVariable Integer id) {
            return ResponseEntity.status(200).build();
    }

    /* method that will delete
           comment to the specified ad*/
    @Operation(
            summary = "Deleting comment to advertisement.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Method deletes the comment to the advertisement."
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment was successfully deleted.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user."
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access is denied."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Advertisement with specified id was not found."
                    )
            },
            tags = "Comments"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@Parameter(description = "Ad id") @PathVariable Integer adId, @Parameter(description = "Comment id") @PathVariable Integer commentId) {
        return ResponseEntity.status(200).build();
    }

    /* method that will update
           comment to the specified ad*/
    @Operation(
            summary = "Updating comment to advertisement.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Method updates the comment to the advertisement."
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment was successfully updated.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user."
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access is denied."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Advertisement with specified id was not found."
                    )
            },
            tags = "Comments"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@Parameter(description = "Ad id") @PathVariable Integer adId, @Parameter(description = "Comment id") @PathVariable Integer commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.status(200).build();
    }
}
