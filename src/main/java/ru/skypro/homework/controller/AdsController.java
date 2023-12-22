package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все объявления",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDTO.class)
                    )


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @GetMapping()
    @Operation(summary = "Получение всех объявлений", description = "getAllAds", tags = {"Объявления"})
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class))


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления", description = "addAd", tags = {"Объявления"})
    public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart MultipartFile image) {
        return ResponseEntity.ok(new AdDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получена информация об объявлении"


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
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении", description = "getAds", tags = {"Объявления"})
    public ResponseEntity<ExtendedAdDTO> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(new ExtendedAdDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Удалено"


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
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления", description = "removeAd", tags = {"Объявления"})
    public ResponseEntity<Void> deleteAd(@Parameter(description = "ID объявления") @PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Объявление изменено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class))


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
    @PatchMapping("/{id}")
    @Operation(summary = "Добавление объявления", description = "addAd", tags = {"Объявления"})
    public ResponseEntity<AdDTO> updateAd(@Parameter(description = "ID объявления") @PathVariable long id,
                                                        @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return ResponseEntity.ok(new AdDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены объявления авторизованого пользователя"


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя", description = "getAdsMe", tags = {"Объявления"})
    public ResponseEntity<AdsDTO> getAdsMe() {
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Картинка объявления изменена",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = AdDTO.class)
                    )


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
    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления", description = "updateImage", tags = {"Объявления"})
    public ResponseEntity<?> updateImage(@Parameter(description = "ID объявления") @PathVariable long id,
                                            @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
