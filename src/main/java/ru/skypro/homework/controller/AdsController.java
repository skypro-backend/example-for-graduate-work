package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    @GetMapping
    public ResponseEntity<Object> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> addAds(@RequestBody Object ads) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getAllMeAds() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<Object> getAdsComment(@PathVariable Integer ad_pk) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<Object> addAdsComment(@PathVariable Integer ad_pk,
                                @RequestBody Object comment) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<Object> deleteAdsComment(@PathVariable Integer ad_pk,
                                   @PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<Object> getAdsComment(@PathVariable Integer ad_pk,
                                   @PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeAds(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAds(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }
}
