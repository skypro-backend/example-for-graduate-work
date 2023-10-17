package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import java.io.IOException;

/**
 * Контроллер для работы с объявлениями
 */
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Объявления", description = "работа с объявлениями")
public class AdsController {
    @Operation (
              summary = "Получение всех объявлений"
    )
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(new AdsDto());
    }

    @Operation(
              summary = "Добавление объявления"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto, @RequestPart("image") MultipartFile image) {
            return new ResponseEntity<>(new AdDto(), HttpStatus.CREATED);
    }

    @Operation(
              summary = "Получение объявления по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdInformation(@PathVariable("id") Integer id) {
            return ResponseEntity.ok(new ExtendedAdDto());
    }

    @Operation(
              summary = "Удаление объявления по id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
            return ResponseEntity.noContent().build();
        }

    @Operation(
              summary = "Изменение объявления"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAdInformation(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
            return ResponseEntity.ok(new AdDto());
    }

    @Operation(
              summary = "Получение всех объявлений пользователя"
    )
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        return ResponseEntity.ok(new AdsDto());
    }

    @Operation(
              summary = "Изменение картинки объявления"
    )
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Integer id, @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(image.getBytes());
    }
}
