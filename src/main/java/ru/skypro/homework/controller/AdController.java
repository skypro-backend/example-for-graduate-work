package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.AdRequestDto;
import ru.skypro.homework.dto.response.AdResponseDto;
import ru.skypro.homework.dto.response.AdsResponseDto;
import ru.skypro.homework.dto.response.ExtendedAdResponseDto;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;

/**
 * REST-контроллер для работы с объявлениями.
 * Предоставляет API для:
 * - получения списка объявлений,
 * - получения конкретного объявления,
 * - работы с объявлениями текущего пользователя,
 * - создания, обновления и удаления объявлений,
 * - загрузки изображений.
 * Бизнес-логика вынесена в {@link AdService}.
 */
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    /**
     * Получение списка всех объявлений.
     * @return список всех объявлений
     */
    @GetMapping
    public ResponseEntity<AdsResponseDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Получение подробной информации об объявлении по ID.
     * @param id идентификатор объявления
     * @return расширенная информация об объявлении
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdResponseDto> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    /**
     * Получение объявлений текущего авторизованного пользователя.
     * @return список объявлений пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<AdsResponseDto> getMyAds() {
        return ResponseEntity.ok(adService.getMyAds());
    }

    /**
     * Создание нового объявления.
     * Принимает:
     * - данные объявления (properties),
     * - изображение объявления.
     * @param properties данные объявления
     * @param image изображение
     * @return созданное объявление
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdResponseDto> addAd(@RequestPart("properties") @Valid AdRequestDto properties, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(properties, image));
    }

    /**
     * Обновление данных объявления.
     * @param id идентификатор объявления
     * @param dto новые данные объявления
     * @return обновлённое объявление
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AdResponseDto> updateAd(@PathVariable Integer id, @RequestBody @Valid AdRequestDto dto) {
        return ResponseEntity.ok(adService.updateAd(id, dto));
    }

    /**
     * Обновление изображения объявления.
     * @param id идентификатор объявления
     * @param image новое изображение
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(@PathVariable Integer id, @RequestPart("image") MultipartFile image) {
        adService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }

    /**
     * Удаление объявления по ID.
     * @param id идентификатор объявления
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }
}
