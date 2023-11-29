package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    private AdDto adsDto = new AdDto(1, "test", 1, 1, "test");
    private List<AdDto> adDtoList = new ArrayList<>(Collections.singleton(adsDto));


    @GetMapping
    public AdsDto getAllAds() {
        return new AdsDto(1,adDtoList);
    }

    @PostMapping
    public AdDto addAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(new AdDto());
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok(new AdsDto());
    }

    @PatchMapping("/{adId}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer adId, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
