package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;

@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AdsController {

    @GetMapping
    public Ads getAds() {
        return new Ads(); // Возвращает пустой объект Ads
    }

    @GetMapping
    public ExtendedAd getAdById(@PathVariable int id) {
        return new ExtendedAd(); // Возвращает пустой объект ExtendedAd
    }

    @PostMapping
    public Ad createAd(@RequestBody Ad ad) {
        return new Ad(); // Возвращает пустой объект Ad
    }

    @DeleteMapping
    public void deleteAd(@PathVariable int id) {
        return ;
    }
}