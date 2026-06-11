package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constant.WebConfigConstant;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Slf4j
@CrossOrigin(value = WebConfigConstant.CORS_ORIGIN)
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Ads.class)))
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = Ad.class)))
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ExtendedAd.class)))
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Ad.class)))
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd ads) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile image) {
        return ResponseEntity.ok(new byte[0]);
    }
}