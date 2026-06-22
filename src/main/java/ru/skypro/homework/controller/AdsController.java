package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

@RestController
@RequestMapping
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AdDTO> addAd(
            @RequestParam("properties") CreateOrUpdateAdDTO properties,
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.status(201).body(new AdDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAdDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDTO createOrUpdateAd) {
        return ResponseEntity.ok(new AdDTO());
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsMe() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PatchMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(new byte[0]);
    }
}
