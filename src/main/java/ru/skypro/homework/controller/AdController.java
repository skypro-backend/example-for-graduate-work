package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

@Slf4j
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "API для управления объявлениями")
public class AdController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение объявления по ID")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return adService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Создание объявления")
    public ResponseEntity<Ad> createAd(@RequestBody CreateOrUpdateAd createAd, Authentication authentication) {
        return adService.createAd(createAd, authentication.getName())
                .map(ResponseEntity.status(HttpStatus.CREATED)::body)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @adService.isAdOwner(#id, authentication.name)")
    @Operation(summary = "Обновление объявления")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateAd updateAd,
                                       Authentication authentication) {
        return adService.updateAd(id, updateAd)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @adService.isAdOwner(#id, authentication.name)")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        if (adService.deleteAd(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Получение объявлений текущего пользователя")
    public ResponseEntity<Ads> getMyAds(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsByUserEmail(authentication.getName()));
    }
}