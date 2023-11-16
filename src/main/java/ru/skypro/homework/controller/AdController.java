package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@RestController
@RequestMapping("/api/ads")
public class AdController {


    private AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam("image") MultipartFile image,
                                    @ModelAttribute CreateOrUpdateAd adDetails) {
        Ad newAd = adService.addAd(image, adDetails);
        return ResponseEntity.status(201).body(newAd);
    }


    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adService.getAllAds();
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Long id) {
        ExtendedAd ad = adService.getAdById(id);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Long id) {
        if (adService.removeAd(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long id, @RequestBody CreateOrUpdateAd adDetails) {
        Ad updatedAd = adService.updateAd(id, adDetails);
        if (updatedAd != null) {
            return ResponseEntity.ok(updatedAd);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getAdsByCurrentUser() {
        List<Ad> ads = adService.getAdsByCurrentUser();
        return ResponseEntity.ok(ads);
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        byte[] updatedImage = adService.updateAdImage(id, image);
        if (updatedImage != null) {
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}