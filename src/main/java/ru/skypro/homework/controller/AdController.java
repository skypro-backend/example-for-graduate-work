package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import java.util.Collection;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity <Collection<AdDto>> getAll() {
        return ResponseEntity.ok(adService.getAll());
    }
    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestBody CreateOrUpdateAdDto adDto) {
        try {
            AdDto ad = adService.addAd(adDto);
            return ResponseEntity.ok(ad);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable int id) {
        try {
            ExtendedAdDto ad = adService.getAdById(id);
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
    public ResponseEntity<AdDto> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAdDto adDto) {
        try {
            AdDto updatedAd = adService.updateAd(id, adDto);
            return ResponseEntity.ok(updatedAd);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsForCurrentUser() {
        try {
            AdsDto ads = adService.getAdsForCurrentUser();
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