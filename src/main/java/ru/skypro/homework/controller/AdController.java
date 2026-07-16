package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.Collections;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления", description = "API для управления объявлениями")
public class AdController {

    @Operation(summary = "Получить все объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<ResponseWrapper<AdDto>> getAllAds() {
        log.info("Вызван метод getAllAds");
        ResponseWrapper<AdDto> wrapper = ResponseWrapper.<AdDto>builder()
                .count(0)
                .results(Collections.emptyList())
                .build();
        return ResponseEntity.ok(wrapper);
    }

    @Operation(summary = "Создать объявление")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = AdDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto properties,
                                       @RequestPart("image") MultipartFile image) {
        log.info("Вызван метод addAd с title: {}", properties.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AdDto());
    }

    @Operation(summary = "Получить объявление по ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ExtendedAdDto.class)))
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        log.info("Вызван метод getAds с id: {}", id);
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @Operation(summary = "Удалить объявление по ID")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        log.info("Вызван метод removeAd с id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновить объявление по ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = AdDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto updateAdDto) {
        log.info("Вызван метод updateAd с id: {}", id);
        return ResponseEntity.ok(new AdDto());
    }

    @Operation(summary = "Получить все объявления авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<AdDto>> getAdsMe() {
        log.info("Вызван метод getAdsMe");
        ResponseWrapper<AdDto> wrapper = ResponseWrapper.<AdDto>builder()
                .count(0)
                .results(Collections.emptyList())
                .build();
        return ResponseEntity.ok(wrapper);
    }

    @Operation(summary = "Обновить картинку объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile image) {
        log.info("Вызван метод updateAdImage с id: {}, файл: {}", id, image.getOriginalFilename());
        return ResponseEntity.ok().build();
    }
}