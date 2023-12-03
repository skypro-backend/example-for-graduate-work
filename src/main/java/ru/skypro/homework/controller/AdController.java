package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.impl.AdServiceImpl;


import java.util.List;


@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class AdController {
    private final AdServiceImpl adService;

    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }


    @GetMapping
    public Ads getAllAds() {

        return adService.getAllAds();
    }

    // Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public Ads getAdsByCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {

        return adService.getAdsByCurrentUser(userDetails);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad addAd(@RequestPart("properties") MultipartFile image, CreateOrUpdateAd adDetails, @AuthenticationPrincipal UserDetails userDetails) {
        return adService.addAd(image, adDetails, userDetails);
    }


    @GetMapping("/{id}")
    public ExtendedAd getFullAd(@PathVariable Integer id) {

        return adService.getFullAd(id);
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public Ad updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd adDetails) {

        return adService.updateAd(id, adDetails);
    }


    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAd(@PathVariable Integer id) {

        return adService.removeAd(id);

    }


    // Обновление картинки объявления
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {

        return ResponseEntity.ok().build();

    }
}


