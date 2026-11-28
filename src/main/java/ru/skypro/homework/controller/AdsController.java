package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "Контроллер для работы с объявлениями")
public class AdsController {

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<Ad> addAd(@RequestBody CreateOrUpdateAd createAd) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads());
    }
}