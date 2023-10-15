package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    /**
     * Метод получает все объявления.
     *
     * @return возвращает ResponsEntity.
     */
    @GetMapping
    public ResponseEntity<?> getAllAds() {
        return adsService.getAllAds();
    }

    /**
     * Метод добавляет новое объявление.
     *
     * @param properties DTO. Включает title, price и description объявления.
     * @param image      принимает изображение.
     * @return возвращает ResponsEntity.
     */
    @PostMapping
    public ResponseEntity<?> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                   @RequestPart("image") MultipartFile image) {
        return adsService.addAd(properties, image);
    }

    /**
     * Метод получает информацию об объявлении по id.
     *
     * @param id идентификатор объявления в БД.
     * @return возвращает ResponsEntity.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAds(@PathVariable Integer id) {
        return adsService.getAds(id);
    }

    /**
     * Метод удаляет объявление по id.
     *
     * @param id идентификатор объявления в БД.
     * @return возвращает ResponsEntity.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removeAd(@PathVariable Integer id) {
        return adsService.removeAd(id);
    }

    /**
     * Метод обновляет информацию об объявлении по id.
     *
     * @param id               идентификатор объявления в БД.
     * @param createOrUpdateAd DTO. Включает title, price и description объявления.
     * @return DTO Ad. Включает author, image, pk, price и title объявления.
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return adsService.updateAds(id, createOrUpdateAd);
    }

    /**
     * @return возвращает объявления авторизованного пользователя.
     */
    @GetMapping(value = "/me")
    public ResponseEntity<?> getAdsMe() {
        return adsService.getAdsMe();
    }

    /**
     * Метод обновляет картинку объявления по id.
     *
     * @param id   идентификатор объявления в БД.
     * @param file принимает изображение.
     * @return бинарный код изображения.
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                         @RequestParam("image") MultipartFile file) {
        return adsService.updateImage(id, file);
    }
}
