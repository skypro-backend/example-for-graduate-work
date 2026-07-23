package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.AdService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления", description = "API для управления объявлениями")
public class AdController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получить все объявления")
    public ResponseEntity<ResponseWrapper<AdDto>> getAllAds() {
        var ads = adService.getAllAds();
        return ResponseEntity.ok(new ResponseWrapper<>(ads.size(), ads));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Создать объявление")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto properties,
                                       @RequestPart("image") MultipartFile image,
                                       Authentication authentication) {
        log.info("POST /ads — создание объявления: {}", properties.getTitle());
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        AdDto created = adService.createAd(properties, image, userDetails.getUserEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить объявление по ID")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAdById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление по ID")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить объявление по ID")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto updateAdDto) {
        return ResponseEntity.ok(adService.updateAd(id, updateAdDto));
    }

    @GetMapping("/me")
    @Operation(summary = "Получить объявления авторизованного пользователя")
    public ResponseEntity<ResponseWrapper<AdDto>> getAdsMe(Authentication authentication) {
        var ads = adService.getMyAds(authentication.getName());
        return ResponseEntity.ok(new ResponseWrapper<>(ads.size(), ads));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить картинку объявления")
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile image) {
        log.info("PATCH /ads/{}/image", id);
        adService.updateAdImage(id, image);
        return ResponseEntity.ok().build();
    }
}