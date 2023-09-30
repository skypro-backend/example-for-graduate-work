package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;

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
