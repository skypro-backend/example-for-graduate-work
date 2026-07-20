package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {


    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            }
    )
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }


    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(
            @RequestPart CreateOrUpdateAd properties,
            @RequestPart MultipartFile image) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Ad());
    }


    @Operation(
            summary = "Получение информации об объявлении"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(
            @PathVariable Integer id) {

        return ResponseEntity.ok(new ExtendedAd());
    }


    @Operation(
            summary = "Удаление объявления"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(
            @PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Обновление информации об объявлении"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateAd ad) {

        return ResponseEntity.ok(new Ad());
    }


    @Operation(
            summary = "Получение объявлений пользователя"
    )
    @GetMapping("/me")
    public ResponseEntity<Ads> getMyAds() {

        return ResponseEntity.ok(new Ads());
    }


    @Operation(
            summary = "Обновление картинки объявления"
    )
    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(
            @PathVariable Integer id,
            @RequestParam MultipartFile image) {

        return ResponseEntity.ok().build();
    }
}