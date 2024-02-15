package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getAdById(id));
    }

    @PostMapping
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.createAd(ad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long id, @RequestBody Ad ad) {
        return ResponseEntity.ok(adService.updateAd(id, ad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
