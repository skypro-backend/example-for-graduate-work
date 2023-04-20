package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDTO;
import ru.skypro.homework.dto.ResponseWrapperAdsDTO;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@AllArgsConstructor
public class AdsController {

    private final AdsService adsService;

    // Получить все объявления
    @GetMapping()
    ResponseEntity<ResponseWrapperAdsDTO> getAllAds() {

        return ResponseEntity.ok(adsService.getAllAds());
    }

    // Добавить объявление
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<AdsDTO> postAd(@RequestPart("image") MultipartFile image,
                                  @RequestPart("properties") CreateAdsDTO properties) {
        return ResponseEntity.ok(adsService.postAd(properties, image));
    }

    // Получить информацию об объявлении
    @GetMapping("/{id}")
    ResponseEntity<FullAdsDTO> getAdInfo(@PathVariable(name = "id") Long adId) {
        return ResponseEntity.ok(adsService.getAdInfo(adId));
    }

    // Удалить объявление
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAd(@PathVariable(name = "id") Long adId) {
        adsService.deleteAd(adId);
        return ResponseEntity.ok().build();
    }

    // Обновить информацию об объявлении
    @PatchMapping("/{id}")
    ResponseEntity<AdsDTO> updateAd(@PathVariable(name = "id") Long adId,
                                    @RequestBody CreateAdsDTO createAdsDTO) {


        return ResponseEntity.ok(adsService.updateAd(adId, createAdsDTO));
    }

    // TODO Получить объявления авторизованного пользователя
    @GetMapping("/me")
    ResponseEntity<ResponseWrapperAdsDTO> getAuthorisedUserAds() {

        return ResponseEntity.ok(adsService.getAuthorisedUserAds());
    }

    // TODO Обновить картинку объявления
    @PatchMapping("/{id}/image")
    ResponseEntity<?> updateAdPicture(@PathVariable(name = "id") Long adId) {
        return ResponseEntity.ok().build();
    }
}
