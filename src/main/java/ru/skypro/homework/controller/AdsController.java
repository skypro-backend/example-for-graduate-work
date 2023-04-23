package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;


import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    @Operation(summary = "Get the whole list of Ads", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Ads",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping
    public ResponseEntity<List<AdsDto>> getAdsAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Adding an ad", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ads Adding",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PostMapping
    public AdsDto addAd(@RequestBody AdsDto adsDto) {
        return new AdsDto();
    }
    @Operation(summary = "Getting information about the ad by id", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Ads",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<AdsDto>> getAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Deleting an ad by id", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ad removed",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> deleteAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Updating information about the ad by id", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the ad has been updated",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/{id}")
    public AdsDto updateAds(@PathVariable Long id) {
        return new AdsDto();
    }
    @Operation(summary = "We receive ads from an authorized user", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ad received",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsAuthorizedUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "update the ad image", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the picture has been updated",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/id/image")
    public AdsDto updateImage(@PathVariable Long id) {
        return new AdsDto();
    }

    /**
     * Viktor
     * @return
     */
    @Operation(summary = "Get ad comments", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of comments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @GetMapping("/{id}/comments")
    public CommentDto getCommentsAds(@RequestBody CommentDto commentDto) {
        System.out.println("Comments");
        return new CommentDto();
    }

    @Operation(summary = "Add comments to the ad", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment Adding",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})

    })
    @PostMapping("/{id}/comments")
    public CommentDto addCommentAds(@RequestBody CommentDto commentDto) {
        System.out.println("Comment add");
        return new CommentDto();
    }

    @Operation(summary = "Delete a comment in an ad by number id", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public CommentDto deleteCommentAds(@RequestBody CommentDto commentDto) {
        System.out.println("Comment deleted");
        return new CommentDto();
    }

    @Operation(summary = "Updating a comment in an ad by number id", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment Updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public CommentDto updateCommentAds(@RequestBody CommentDto commentDto) {
        System.out.println("Update comment");
        return new CommentDto();
    }

}
