package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdService adService;
    private final ImageService imageService;

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
        return ResponseEntity.ok(adService.getAllAds());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ok",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class))


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
            )

            )

    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления", description = "addAd", tags = {"Объявления"})
    public ResponseEntity<AdDTO> addAd(@RequestPart(value = "properties", required = false) CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile image,
                                       Authentication authentication) {
        return ResponseEntity.ok(adService.addAd(createOrUpdateAdDTO, image, authentication));
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
    @Operation(summary = "Получение информации об объявлении", description = "getAd", tags = {"Объявления"})
    public ResponseEntity<ExtendedAdDTO> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getAd(id));
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
    public ResponseEntity<Void> deleteAd(@Parameter(description = "ID объявления")
                                         @PathVariable long id,
                                         Authentication authentication) throws AccessDeniedException {
        adService.deleteAd(id,authentication);
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
    @Operation(summary = "Обновление информации об объявлении", description = "updateInfoAd", tags = {"Объявления"})
    public ResponseEntity<AdDTO> updateAd(@Parameter(description = "ID объявления") @PathVariable long id,
                                          @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                          Authentication authentication) throws AccessDeniedException {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAdDTO,authentication));
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
    public ResponseEntity<AdsDTO> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
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
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления", description = "updateImage", tags = {"Объявления"})
    public ResponseEntity<?> updateImage(@Parameter(description = "ID объявления") @PathVariable("id") long id,
                                         @RequestPart("image") MultipartFile image, Authentication authentication){
        adService.updateAdImage(id,image,authentication);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    @Operation(summary = "Получение картинки объявления", description = "getAdsImage", tags = {"Объявления"})
    public ResponseEntity<byte[]> getAdsImage(@PathVariable("id") long id) {
        byte[] image = imageService.getImage(id).getData();
        return ResponseEntity.ok(image);
    }
}
