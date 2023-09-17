package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    //здесь будет зависимость от AdsService

    @GetMapping("/")
    public ResponseEntity<Ads> getAllAds() {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new Ads(), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> postAd(@RequestBody CreateOrUpdateAd properties,
                                     @RequestParam("image") MultipartFile file) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new Ad(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new ExtendedAd(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable int id) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> patchAdById(@PathVariable int id,
                                          @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new Ad(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getMyAds() {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new Ads(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> patchAdsImageById(@PathVariable int id,
                                                  @RequestParam("image") MultipartFile file) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(HttpStatus.OK);
        //todo: уточнить про возвращаемый октет-стрим и переделать!!!
    }
}
