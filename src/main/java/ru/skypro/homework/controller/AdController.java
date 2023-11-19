package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class AdController {


    @GetMapping
    public Ads getAllAds() {
        return new Ads(1, List.of(new Ad(1, " ", 1, 100, "text")));
    }

    // Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public Ad getAdsByCurrentUser() {

        return new Ad(1, " ", 1, 100, "text");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad addAd(@RequestPart("properties") MultipartFile image, @ModelAttribute CreateOrUpdateAd adDetails) {
        return new Ad(1, " ", 1, 100, "text");
    }


    @GetMapping("/{id}")
    public ExtendedAd getFullAd(@PathVariable Long id) {

        return new ExtendedAd(1, "Bob", "Johnston", "description",
                "@mail.com", " ", "89886085316", 100, "text");
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public Ad updateAd(@PathVariable Long id, @RequestBody CreateOrUpdateAd adDetails) {

        return new Ad(1, " ", 1, 100, "text");
    }


    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAd(@PathVariable Long id) {

        return ResponseEntity.ok().build();

    }


    // Обновление картинки объявления
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {

        return ResponseEntity.ok().build();

    }
}


