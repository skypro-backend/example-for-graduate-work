package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Управление карточками товаров и услуг платформы Sopetov")
public class AdsController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<ExtendedAd> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                            @RequestPart("image") MultipartFile image,
                                            Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(properties, image, authentication.getName()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable int id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> removeAd(@PathVariable int id) {
        adService.removeAd(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<ExtendedAd> updateAds(@PathVariable int id,
                                                @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(adService.updateAds(id, createOrUpdateAd));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication.getName()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public ResponseEntity<byte[]> updateImage(@PathVariable int id,
                                              @RequestParam MultipartFile image) {
        return ResponseEntity.ok(adService.updateImage(id, image));
    }
}