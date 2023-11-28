package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

//    private final AdsService adsService;
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return new ResponseEntity<>(new Ads(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties, @RequestPart("image") MultipartFile image) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<ExtendedAd> getAds (@PathVariable Integer Id) {
        return new ResponseEntity<>(new ExtendedAd(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer Id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<CreateOrUpdateAd> updateAds(@RequestBody CreateOrUpdateAd updateAd) {
        return new ResponseEntity<>(new CreateOrUpdateAd(), HttpStatus.OK);
    }

    @GetMapping(value = "/me")
    public ResponseEntity<Ads> getAdsMe() {
        return new ResponseEntity<>(new Ads(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{Id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer Id,
                                              @RequestParam MultipartFile image) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(image.getBytes());
    }
}
