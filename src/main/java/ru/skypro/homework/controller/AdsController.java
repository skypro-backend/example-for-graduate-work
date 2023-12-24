package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads.*;
import ru.skypro.homework.dto.Comments.CommentDto;
import ru.skypro.homework.dto.Comments.CommentsDto;
import ru.skypro.homework.dto.Comments.CreateOrUpdateComment;

@RestController
@RequestMapping
public class AdsController {

    @Operation(summary = "Получение всех объявлений", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = AdsDto.class)))
    })
    @GetMapping("/ads")
    public AdsDto getAllAds() {
        return new AdsDto();
    }

    @Operation(summary = "Добавление объявления", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/ads")
    public AdDto addAd(
            @RequestParam(name = "image", required = true) String image,
            @Parameter(name = "properties", required = true)
            @RequestBody CreateOrUpdateAdDto properties) {
        return new AdDto();
    }

    @Operation(summary = "Получение информации об объявлении", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = ExtendedAdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/ads/{id}")
    public ExtendedAdDto getAds(
            @Parameter(name = "id", required = true)
            @PathVariable int id) {
        return new ExtendedAdDto();
    }

    @Operation(summary = "Удаление объявления", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "No Content",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/ads/{id}")
    public ResponseEntity<?> removeAd(
            @Parameter(name = "id", required = true)
            @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Обновление информации об объявлении", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("/ads/{id}")
    public AdDto updateAds(
            @Parameter(name = "id", required = true)
            @PathVariable int id,
            @RequestBody CreateOrUpdateAdDto dto) {
        return new AdDto();
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = AdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/ads/me")
    public AdsDto getAdsMe() {
        return new AdsDto();
    }

    @Operation(summary = "Обновление картинки объявления", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("/ads/{id}/image")
    public String updateImage(
            @Parameter(name = "id", required = true)
            @PathVariable int id,
            @Parameter(name = "image", required = true)
            @RequestParam String image) {
        return "1243sq";
    }

    @Operation(summary = "Получение комментариев объявления", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/ads/{id}/comments")
    public CommentsDto getComments(
            @Parameter(name = "id", required = true)
            @PathVariable int id) {
        return new CommentsDto();
    }

    @Operation(summary = "Добавление комментария к объявлению", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("ads/{id}/comments")
    public CommentDto addComment(
            @Parameter(name = "id", required = true)
            @PathVariable int id,
            @RequestBody CreateOrUpdateComment text) {
        return new CommentDto();
    }

    @Operation(summary = "Удаление комментария", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @Parameter(name = "adId", required = true)
            @PathVariable int adId,
            @Parameter(name = "commentId", required = true)
            @PathVariable int commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Обновление комментария", tags = {"Комментарии"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public CommentDto updateComment(
            @Parameter(name = "adId", required = true)
            @PathVariable int adId,
            @Parameter(name = "commentId", required = true)
            @PathVariable int commentId,
            @RequestBody CreateOrUpdateComment text) {
        return new CommentDto();
    }
}
