package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdsService;




@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdsСontroller {

    private final AdsService adsService;



    @GetMapping(value = "/getAd")
    public ResponseEntity<Ad>getAd(){
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping(value = "/{id}/updateAds")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(new Ad());
    }
    @GetMapping(value = "/getAdsImage")
    public ResponseEntity<Ads>getAdsImage(){
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "/{id}/updateImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile adImageFile) {
        return ResponseEntity.ok("");
    }
}
