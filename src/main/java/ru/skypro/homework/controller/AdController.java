package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    @GetMapping()
    public ResponseEntity<Collection<Ad>> getAllAds() {
        Collection<Ad> ads = adService.findAll();
        if (ads.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ads);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Ad>> getById(@PathVariable Long id) {
        Optional<Ad> foundAd = adService.findById(id);
        if (foundAd.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundAd);
    }

    @GetMapping("/me")
    public ResponseEntity<Optional<Ad>> getAdByMe() {
        Optional<Ad> ads = adService.getAdByAuthUser();
        return ResponseEntity.ok(ads);
    }
    @PostMapping()
    public ResponseEntity<Void> addAd(@RequestBody CreateOrUpdateAdDTO ad, @RequestBody byte[] img) {
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
        adService.addAd(ad, img);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateAd(@PathVariable Long id, @RequestBody CreateOrUpdateAdDTO ad) {
        Optional<Ad> foundAd = adService.findById(id);
        if (foundAd.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adService.updateAd(ad);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<Void> updateImageAd(@PathVariable Long id, @RequestBody byte[] img) {
        Optional<Ad> foundAd = adService.findById(id);
        if (foundAd.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adService.updateImage(img);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable Long id) {
        Optional<Ad> ad = adService.findById(id);
        if (ad.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adService.deleteAd(ad);
        return ResponseEntity.ok().build();
    }

}
