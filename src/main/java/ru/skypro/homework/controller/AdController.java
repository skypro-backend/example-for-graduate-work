package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdController {
    private AdService adService;

    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new Ads());
    }

    @PostMapping("/ads")
    public ResponseEntity<Ad> postAd(@RequestBody Ad ad) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new Ad());
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new ExtendedAd());
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{id}")
    public ResponseEntity<Ad> editAd(@RequestBody CreateOrUpdateAd ad) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new Ad());
    }

    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAllMyAds() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new Ads());
    }

    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<String> updateUserImage(@PathVariable int id,
                                                  @RequestBody MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new Image().getLink());
    }

}

