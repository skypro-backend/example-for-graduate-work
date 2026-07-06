package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.Ads;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import ru.skypro.homework.service.IAdService;

import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    private final IAdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(adService.getAllAds(offset, limit));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение моих объявлений")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ads> getMyAds(Authentication auth) {
        return ResponseEntity.ok(adService.getMyAds(auth));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр объявления")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable long id) {
        return adService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAd(
            @RequestPart(required = true) CreateOrUpdateAd data,
            @RequestPart(value = "image", required = false) MultipartFile image,
            Authentication auth) {

        Ad ad = adService.createAd(data, auth);

        if (image != null) {
            this.saveImage(ad.getPk(), image);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", ad.getPk()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(
            @PathVariable long id,
            @RequestPart MultipartFile image,
            Authentication auth) {

        adService.updateImage(id, image, auth);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Редактирование объявления")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Ad> updateAd(
            @PathVariable long id,
            @RequestBody CreateOrUpdateAd data,
            Authentication auth) {
        return ResponseEntity.ok(adService.updateAd(id, data, auth));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteAd(
            @PathVariable long id,
            Authentication auth) {
        adService.deleteAd(id, auth);
        return ResponseEntity.noContent().build();
    }

    private void saveImage(long adId, MultipartFile image) {
        System.out.println("Saving image for ad#" + adId);
    }
}