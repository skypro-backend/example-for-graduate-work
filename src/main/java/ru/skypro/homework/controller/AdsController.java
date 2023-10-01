package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsController {

    @PostMapping
    public Ad ad(@RequestBody Ad ad) {
        return new Ad();
    }

    @GetMapping
    public Ads ads(@PathVariable long Id) {
        return new Ads();
    }
}
