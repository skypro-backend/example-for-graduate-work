package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер по работе с объявлениями
 */
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    // здесь будут поля сервисов

    public AdsController() {
    }

    // здесь будет конструктор с параметрами

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    public ResponseEntity<Collection<?>> getAllAds() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(
            summary = "Добавление объявления"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<?> addAd() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> getAds() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления"
    )
    @ApiResponse(
            responseCode = "204",
            description = "No content"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> removeAd() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление информации об объявлении"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> updateAds() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение объявлений авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<?> getAdsMe() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/image")
    @Operation(
            summary = "Обновление картинки объявления"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    public ResponseEntity<?> updateImage() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }
}