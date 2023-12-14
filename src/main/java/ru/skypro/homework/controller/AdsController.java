package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.ads.*;


@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        //  логика
        Ads ads = null;// получения всех объявлений
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam("image") String image, @RequestBody CreateOrUpdateAd ad) {
        // логика
        if (image != null && ad != null) { // условие проверки наличия авторизации
            Ad newAd = null; //  добавления объявления
            return new ResponseEntity<>(newAd, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable("id") int id) {
        ExtendedAd ad = null;// получения объявления по id
        if (ad != null) {
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
