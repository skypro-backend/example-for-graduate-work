package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/ads")
public class AdController {
    @PostMapping
    public ResponseEntity addAd(@RequestBody Ad ad) {
        return ResponseEntity.ok(ad);
    }
}
