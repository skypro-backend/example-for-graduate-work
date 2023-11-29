package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping
    public ResponseEntity<Ads>getAllAds(){
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body(new Ad());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ads>getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new Ads());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ad> removeAd(@PathVariable Integer adId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping(value = "/me")
    public ResponseEntity<Ads>getAdsMe(){
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok("");
    }
}




























