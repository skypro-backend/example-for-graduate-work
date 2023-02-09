package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Comment.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @GetMapping(value = "/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComments(@PathVariable(name = "ad_pk")
                                               @NotBlank(message = "ad_pk не должен быть пустым")
                                               @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                               @Parameter(description = "Идентификатор объявления",
                                                       example = "1") String adPk,
                                               @PathVariable(name = "id")
                                               @NotBlank(message = "id не должен быть пустым")
                                               @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                               @Parameter(description = "Идентификатор комментария",
                                                       example = "1") int id) {
        return ResponseEntity.ok(adsService.getComments(adPk, id));
    }

    @ApiResponses({
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
    @DeleteMapping(value = "/{ad_pk}/comments/{id}")
    public void deleteComments(@PathVariable(name = "ad_pk")
                                         @NotBlank(message = "ad_pk не должен быть пустым")
                                         @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                         @Parameter(description = "Идентификатор объявления",
                                                 example = "1") String adPk,
                                         @PathVariable(name = "id")
                                         @NotBlank(message = "id не должен быть пустым")
                                         @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                         @Parameter(description = "Идентификатор комментария",
                                                 example = "1") int id) {
        adsService.deleteComments(adPk, id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Comment.class))
                    }
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
    @PatchMapping(value = "/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable(name = "ad_pk")
                                                  @NotBlank(message = "ad_pk не должен быть пустым")
                                                  @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                                  @Parameter(description = "Идентификатор объявления",
                                                          example = "1") String adPk,
                                                  @PathVariable(name = "id")
                                                  @NotBlank(message = "id не должен быть пустым")
                                                  @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                                  @Parameter(description = "Идентификатор комментария",
                                                          example = "1") int id,
                                                  @RequestBody Comment comment) {
        return ResponseEntity.ok(adsService.updateComments(adPk, id, comment));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            )
    })
    @DeleteMapping(value = "/{id}")
    public void removeAds(@PathVariable(name = "id")
                               @NotBlank(message = "id не должен быть пустым")
                               @Min(value = 1, message = "Идентификатор должен быть больше 0")
                               @Parameter(description = "Идентификатор объявления",
                                       example = "1") int id) {
        adsService.removeAds(id);
    }
}
