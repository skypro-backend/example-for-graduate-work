package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.*;


@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        //  логика
        AdsDTO adsDTO = null;// получения всех объявлений
        return new ResponseEntity<>(adsDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdDTO> addAd(@RequestParam("image") String image, @RequestBody CreateOrUpdateAdDTO ad) {
        // логика
        if (image != null && ad != null) { // условие проверки наличия авторизации
            AdDTO newAdDTO = null; //  добавления объявления
            return new ResponseEntity<>(newAdDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAdById(@PathVariable("id") int id) {
        ExtendedAdDTO ad = null;// получения объявления по id
        if (ad != null) {
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}