package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

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
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
                    }
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
}
