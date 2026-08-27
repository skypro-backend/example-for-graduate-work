package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Управление карточками товаров и услуг платформы Sopetov")
public class AdsController {

    private final AdService adService;
    private final ImageService imageService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
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
    @PreAuthorize("principal.username == @adServiceImpl.getAd(#id).email or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> removeAd(@PathVariable int id) {
        adService.removeAd(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("principal.username == @adServiceImpl.getAd(#id).email or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<Ad> updateAds(@PathVariable int id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(adService.updateAdsBasic(id, createOrUpdateAd));
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

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @Operation(summary = "Получение картинки из файла")
    public ResponseEntity<byte[]> getImageFromFile(@PathVariable String fileName) {
        try {
            return ResponseEntity.ok(imageService.getImage(fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}