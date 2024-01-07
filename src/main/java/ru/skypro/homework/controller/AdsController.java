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

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")

public class AdsController {

    @GetMapping()
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //+ 401 Unauthorized
    public ResponseEntity<Ad> addAd(@RequestBody CreateOrUpdateAd properties, @RequestBody MultipartFile image) {
        return new ResponseEntity<>(new Ad(), HttpStatus.CREATED);
    }

    @GetMapping("{id}") //+ 401 Unauthorized + 404 Not found
    public ResponseEntity<ExtendedAd> getInformationByAd(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("{id}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<?> deleteAd(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<Ad> updateAdInformation(@PathVariable Integer id, @RequestBody CreateOrUpdateAd properties) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me") //+ 401 Unauthorized
    public ResponseEntity<Ads> getAdsFromAuthorizedUser() {
        return ResponseEntity.ok(new Ads());
    }

    @PutMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    public ResponseEntity<byte[]> updateAdPicture(@PathVariable Integer id, @RequestBody MultipartFile image) {
        HttpHeaders headers = new HttpHeaders();
        byte[] data = {};
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(data);
    }
}