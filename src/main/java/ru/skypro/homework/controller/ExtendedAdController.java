package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.ExtendedAd;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class ExtendedAdController {

    @PostMapping("/ExtendedAd")
    public ExtendedAd extendedAd(@RequestBody ExtendedAd extendedAd) {
        return new ExtendedAd();
    }
}
