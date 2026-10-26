package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;

import java.util.Collections;

@RestController
@RequestMapping("/ads")
@Tag(name = "объявления")
public class AdsController {

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<AdsDto> getAllAds(){
        return ResponseEntity.ok(new AdsDto(0, Collections.emptyList()));
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties")CreateOrUpdateAdDto properties,
                                       @RequestPart("image")MultipartFile image){
        return ResponseEntity.status(HttpStatus.CREATED).body(new AdDto(1, null, 1, 100, "Test"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id){
        return ResponseEntity.ok(new ExtendedAdDto(id, "Ivan", "Ivanov", "desc", "email@mail.ru",
                null, "+79999999999", 100, "Title"));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id){
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto dto){
        return ResponseEntity.ok(new AdDto(1, null, id, dto.price(), dto.title()));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизированного пользователя")
    public ResponseEntity<AdsDto> getAdsMe(){
        return ResponseEntity.ok(new AdsDto(0, Collections.emptyList()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id, @RequestPart("image") MultipartFile image){
        return ResponseEntity.ok(new byte[0]);
    }
}
