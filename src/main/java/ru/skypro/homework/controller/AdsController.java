package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    /**
     * Метод обновляет информацию об объявлении.
     *
     * @param id               порядковый номер объявления в БД.
     * @param createOrUpdateAd DTO. Включает title, price и description объявления.
     * @return DTO Ad. Включает author, image, pk, price и title объявления.
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(new Ad());
    }

    /**
     * @return возвращает объявления авторизованного пользователя.
     */
    @GetMapping(value = "/me")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads());
    }

    /**
     * Метод обновляет картинку объявления.
     *
     * @param id        порядковый номер объявления в БД.
     * @param imageFile принимает изображение.
     * @return бинарный код изображения.
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile imageFile) {
        return ResponseEntity.ok("Uploaded");
    }

    /**
     * Метод получения всех объявлений
     * @return возвращает ResponsEntity
     */
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    /**
     * Метод добавления нового объявления
     * @param createOrUpdateAd
     * @param imageFile              принимает изображение.
     * @return возвращает ResponsEntity
     */
    // postman не отрабатывает
    @PostMapping
    public ResponseEntity<Ad> addAds(@RequestBody CreateOrUpdateAd createOrUpdateAd,
                                     @RequestParam MultipartFile imageFile) {
        return ResponseEntity.ok(new Ad());
    }

    /**
     * метод удаления объявления по id
     * @param id            идентификатор объявления
     * @return возвращает ResponsEntity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAds(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * метод получения информации объявления по id
     * @param id идентификатор объявления
     * @return возвращает ResponsEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExtendedAd> getInfoAdsById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAd());
    }
}
