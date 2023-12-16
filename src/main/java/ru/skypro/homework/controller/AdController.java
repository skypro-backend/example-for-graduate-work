package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id) {
        try {
            ExtendedAd ad = adService.getAdById(id);
            return ResponseEntity.ok(ad);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        try {
            adService.deleteAd(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAd adDto) {
        try {
            Ad updatedAd = adService.updateAd(id, adDto);
            return ResponseEntity.ok(updatedAd);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsForCurrentUser() {
        try {
            Ads ads = adService.getAdsForCurrentUser();
            return ResponseEntity.ok(ads);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateAdImage(@PathVariable int id, @RequestParam("image") MultipartFile image) {
        try {
            byte[] imageBytes = image.getBytes();
            adService.updateAdImage(id, imageBytes);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}