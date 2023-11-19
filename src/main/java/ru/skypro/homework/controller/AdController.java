package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.service.AdService;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("ads/")
public class AdController {
    AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {

        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam CreateOrUpdateAd properties, @RequestParam MultipartFile image) {
        Ad ad = adService.addAd(properties, image); // метод в разработке
        return ResponseEntity.ok(ad);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable("id") Integer id) {

        ExtendedAd ad = adService.getAds(id);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeAd(@PathVariable("id") Integer id) {

        return (adService.removeAd(id)) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAd dto) {

        Ad ad = adService.updateAds(id, dto);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }
}