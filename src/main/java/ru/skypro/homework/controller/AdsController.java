package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.impl.AdsService;

import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok().body(adsService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<AdDto> addAd(@RequestPart CreateOrUpdateAdDto properties, @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(adsService.addAd(properties, image));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok().body(adsService.getAds(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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
                                       @RequestBody CreateOrUpdateAdDto newAds) {
        return ResponseEntity.ok().body(adsService.updateAds(id, newAds));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok().body(adsService.getAdsMe());
    }

    @PatchMapping(value = "/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE})
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    @ApiResponse(responseCode = "404",
            description = "Операция не найдена")

    public ResponseEntity<byte[]> updateImage(@PathVariable int id, @RequestBody MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adsService.updateImage(id, image));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}