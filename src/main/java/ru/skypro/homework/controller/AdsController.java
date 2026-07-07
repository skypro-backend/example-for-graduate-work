package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdsService;

import java.util.Collections;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {

    private final AdsService adsService;

    private static final Integer MOCK_ID = 1;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Ads> getAllAds() {
        log.info("Получение списка всех объявлений");
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(Collections.emptyList());
        return ResponseEntity.ok(ads);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image) {

        log.info("Создание объявления. Заголовок: {}, Цена: {}, Файл: {}",
                properties.getTitle(), properties.getPrice(), image.getOriginalFilename());

        Ad ad = new Ad();
        ad.setPk(MOCK_ID); // Заглушка ID
        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setImage("ссылка_на_картинку");
        return ResponseEntity.status(201).body(ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        log.info("Получение объявления с ID: {}", id);
        ExtendedAd ad = new ExtendedAd();
        ad.setPk(id);
        ad.setTitle("Пример заголовка");
        ad.setDescription("Пример описания");
        // Остальные поля будут null, это нормально для заглушки
        return ResponseEntity.ok(ad);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<?> removeAd(@PathVariable Integer id) {
        log.info("Удаление объявления с ID: {}", id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd dto) {
        log.info("Обновление объявления ID: {}. Новые данные: {}", id, dto);
        Ad ad = new Ad();
        ad.setPk(id);
        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизированного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Ads> getAdsMe() {
        log.info("Получение объявлений авторизованного пользователя");
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(Collections.emptyList());
        return ResponseEntity.ok(ads);
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<?> updateImage(@PathVariable Integer id, @RequestPart("image") MultipartFile image) {
        log.info("Обновление картинки для объявления ID: {}. Файл: {}", id, image.getOriginalFilename());
        return ResponseEntity.ok().build();
    }

}
