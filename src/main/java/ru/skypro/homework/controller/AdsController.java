package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdServiceImpl adService;

    @Operation(
            summary = "Получение всех объявлений"
    )
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(
            summary = "Добавление объявления"
    )
    @PostMapping(/*consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public ResponseEntity<AdDto> addAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto /*, @RequestPart("image") MultipartFile image*/) {
            return new ResponseEntity<>(adService.addAd(createOrUpdateAdDto, null), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Получение объявления по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdInformation(@PathVariable("id") Integer id) {
            return ResponseEntity.ok(adService.getAdInformation(id));
    }

    @Operation(
            summary = "Удаление объявления по id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
        }

    @Operation(
            summary = "Изменение объявления"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAdInformation(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdDto adDto = adService.updateAdInformation(id, createOrUpdateAdDto);
        return ResponseEntity.ok(adDto);
    }

    @Operation(
            summary = "Получение всех объявлений пользователя"
    )
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        AdsDto authorizedUserAds = adService.getAuthorizedUserAds();
        return ResponseEntity.ok(authorizedUserAds);
    }

    @Operation(
            summary = "Изменение картинки объявления"
    )
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Integer id, @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(image.getBytes());
    }
}
