package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads.AdDto;
import ru.skypro.homework.dto.Ads.AdsDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений", operationId = "getAllAds")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = AdsDto.class))
            )
    })
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя", operationId = "getAdsMe")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = AdsDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsByCurrentUser(authentication.getName()));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления", operationId = "addAd")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = AdDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdDto> addAd(
            @Parameter(description = "Данные объявления", required = true)
            @RequestPart("properties") CreateOrUpdateAdDto properties,
            @Parameter(description = "Изображение объявления", required = true)
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) {
        AdDto created = adService.createAd(authentication.getName(), properties, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении", operationId = "getAds")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = ExtendedAdDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<ExtendedAdDto> getAds(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления", operationId = "removeAd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<Void> removeAd(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer id,
            Authentication authentication
    ) {
        adService.deleteAd(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении", operationId = "updateAds")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = AdDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<AdDto> updateAds(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Данные для обновления", required = true)
            @RequestBody CreateOrUpdateAdDto updateAd,
            Authentication authentication
    ) {
        return ResponseEntity.ok(adService.updateAd(authentication.getName(), id, updateAd));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления", operationId = "updateImage")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<byte[]> updateImage(
            @Parameter(description = "ID объявления", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Новое изображение", required = true)
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) {
        return ResponseEntity.ok(adService.updateAdImage(authentication.getName(), id, image));
    }
}
