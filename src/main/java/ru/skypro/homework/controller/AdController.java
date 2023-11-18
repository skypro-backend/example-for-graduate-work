package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.service.AdService;

@RestController("/ad")
public class AdController {
    AdService adService;
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<AdEntity> createAd(@RequestBody AdEntity adEntity) {
        AdEntity ad = adService.createAd(adEntity);
        if (ad == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(ad);
    }

    @PutMapping
    public ResponseEntity<AdEntity> updateAd(@RequestBody AdEntity adEntity) {
        AdEntity ad = adService.updateAd(adEntity);
        if (ad == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(ad);
    }
}
