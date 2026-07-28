package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.ApiConstants;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

@Tag(name = "Объявления")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdService adService;

    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping(consumes = ApiConstants.MULTIPART_FORM_DATA)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") @Valid CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image,
                                    Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adService.addAd(properties, image, authentication));
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAds(id));
    }

    @Operation(summary = "Удаление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id, Authentication authentication) {
        adService.removeAd(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                        Authentication authentication) {
        return ResponseEntity.ok(adService.updateAds(id, createOrUpdateAd, authentication));
    }

    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @PatchMapping(value = "/{id}/image", consumes = ApiConstants.MULTIPART_FORM_DATA)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile image,
                                              Authentication authentication) {
        return ResponseEntity.ok(adService.updateImage(id, image, authentication));
    }
}