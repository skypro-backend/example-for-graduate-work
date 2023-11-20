package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    AdService adService;
    AuthServiceImpl authService;

    public AdController(AdService adService, AuthService authService) {
        this.adService = adService;
        this.authService = (AuthServiceImpl) authService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {

        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam CreateOrUpdateAd properties, @RequestParam MultipartFile image) {
        Ad ad = adService.addAd(properties, image); // метод в разработке
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable("id") Integer id) {

        ExtendedAd ad = adService.getAds(id);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeAd(@PathVariable("id") Integer id) {

        return (adService.removeAd(id)) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAd dto) {

        Ad ad = adService.updateAds(id, dto);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {

        if (authService.getLogin() != null) {   //если пользователь авторизовался

            String username = authService.getLogin().getUsername();
            return ResponseEntity.ok(adService.getAdsMe(username));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity updateImage(@PathVariable("id") Integer id,
                                      @RequestParam MultipartFile image) {
        if (authService.getLogin() != null) {
            return ResponseEntity.ok(adService.updateImage(id, image)); // todo продумать, что метод возвращает
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return null; //todo разобраться с ошибками 403 и 404, как и в остальных методах выше, если есть
    }
}