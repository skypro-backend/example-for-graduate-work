package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.AdDTO;

@RestController
@RequestMapping("/ads")
public class AdController {
    @PostMapping
    public ResponseEntity addAd(@RequestBody AdDTO ad) {
        return ResponseEntity.ok(ad);
    }
}
