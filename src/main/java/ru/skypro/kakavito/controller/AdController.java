package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.service.AdService;

import java.io.IOException;

/**
 * Класс для управления потоком данных при работе с объявлениями
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    /**
     * Запрос на получение списка объявлений
     *
     * @return AdsDTO
     * @see AdsDTO
     */
    @GetMapping()
    public ResponseEntity<AdsDTO> findAllAds() {
        AdsDTO ads = adService.findAllAds();
        if (ads.getCount() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(ads);
    }

    /**
     * Запрос на получение конкретного объявления
     *
     * @param id
     * @return ExtendedAdDTO
     * @see ExtendedAdDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getById(@PathVariable int id) {
        ExtendedAdDTO ad = adService.findById(id);
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ad);
    }

    /**
     * Запрос на получение списка объявлений текущего пользователя
     *
     * @return AdsDTO
     * @see AdsDTO
     */
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdByMe() {
        return ResponseEntity.ok(adService.getAdByAuthUser());
    }

    /**
     * Запрос на создание объявления
     *
     * @param createOrUpdateAdDTO
     * @param imageFile
     * @return AdDTO
     * @throws IOException
     * @throws ImageSizeExceededException
     * @see AdDTO
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile imageFile) throws IOException, ImageSizeExceededException {
        AdDTO ad = adService.addAd(createOrUpdateAdDTO, imageFile);
        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    /**
     * Запрос на редактирование конкретного объявления
     *
     * @param id
     * @param createOrUpdateAdDTO
     * @return AdDTO
     * @see AdDTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable int id,
                                          @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        AdDTO updatedAd = adService.updateAd(id, createOrUpdateAdDTO);
        if (updatedAd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedAd);
    }

    /**
     * Запрос на изменение картинки объявления
     *
     * @param pk
     * @param image
     * @return void
     * @throws IOException
     * @see Image
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateAdImage(@PathVariable int pk,
                                              @RequestBody MultipartFile image) throws IOException {
        adService.updateAdImage(pk, image);
        return ResponseEntity.ok().build();
    }

    /**
     * Запрос на удаление конкретного объявления
     *
     * @param id
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable int id) {
        adService.deleteAd(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }
}
