package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;



@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsСontroller {

    private final AdsService adsService;


    @GetMapping(value = "/me")
    public ResponseEntity<Ad>getAdsMe(){
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping(value = "/me")
    public ResponseEntity<Ads>getAdsImage(){
        return ResponseEntity.ok(new Ads());
    }
    @GetMapping
    public ResponseEntity<Ads>getAllAds(){
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ads>getAds(@PathVariable Integer id,
                                     @RequestBody CreateOrUpdateAd createOrUpdateAd){
        return ResponseEntity.ok(new Ads());
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(new Ad());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@PathVariable Integer id,
                                    @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.status(HttpStatus.OK).body(new Ad());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ad> removeAd(@PathVariable Integer adId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile adImageFile) {
        return ResponseEntity.ok("");
    }
}
