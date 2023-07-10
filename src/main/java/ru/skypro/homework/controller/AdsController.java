package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdsService;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok().body(adsService.getAllAds());
    }

    @PostMapping(value = "/createOrUpdateAd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<Ad> addAd(@RequestPart CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(adsService.addAd(properties, image));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok().body(adsService.getAds(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponse(responseCode = "204",
            description = "Обяыление удалено")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<?> removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<?> updateAds(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateAd newAds) {
        return ResponseEntity.ok().body(adsService.updateAds(id, newAds));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?> getAdsMe() {
        return ResponseEntity.ok().body(adsService.getAdsAllUser());
    }

    @PatchMapping("ads/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                         @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().body(adsService.updateImage(id, image));
    }
}
