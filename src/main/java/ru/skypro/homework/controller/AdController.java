package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    @GetMapping()
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adService.findAll();
        if (ads.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ads);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ad> getById(@PathVariable Long id) {
        Ad foundAd = adService.findById(id);
        if (foundAd == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundAd);
    }

    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getAdByMe() {
        List<Ad> ads = adService.getAdByAuthUser();
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
        Ad foundAd = adService.findById(id);
        if (foundAd == null) {
            return ResponseEntity.notFound().build();
        }
        adService.updateAd(ad);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<Void> updateImageAd(@PathVariable Long id, @RequestBody byte[] img) {
        Ad foundAd = adService.findById(id);
        if (foundAd == null) {
            return ResponseEntity.notFound().build();
        }
        adService.updateImage(img);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable Long id) {
        Ad ad = adService.findById(id);
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
        adService.deleteAd(ad);
        return ResponseEntity.ok().build();
    }

}
