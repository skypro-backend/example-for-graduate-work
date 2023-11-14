package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;

@RestController
public class AdsController {
    @GetMapping("/ads")
    public Ads getAds() {
        return new Ads(); // Возвращает пустой объект Ads
    }

    @GetMapping("/ads/{id}")
    public ExtendedAd getAdById(@PathVariable int id) {
        return new ExtendedAd(); // Возвращает пустой объект ExtendedAd
    }

    @PostMapping("/ads")
    public Ad createAd(@RequestBody Ad ad) {
        return new Ad(); // Возвращает пустой объект Ad
    }

    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable int id) {
        // Удаление объявления
    }
}