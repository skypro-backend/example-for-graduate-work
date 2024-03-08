package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto dto = new AdsDto();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestBody AdDto dto) {
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        ExtendedAdDto dto = new ExtendedAdDto();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAds(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAdDto> updateAds(@PathVariable Integer id) {
        CreateOrUpdateAdDto dto = new CreateOrUpdateAdDto();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        AdsDto dto = new AdsDto();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id, MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
