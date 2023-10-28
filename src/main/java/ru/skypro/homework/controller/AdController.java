package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.AdsUserDetails;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.impl.AdServiceImpl;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdServiceImpl adService;


    //    Получение всех объявлений
    @GetMapping()
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    //Добавление объявления
    @PostMapping()
    public AdDTO addAd(@RequestBody @Valid CreateOrUpdateAd createOrUpdateAdDTO,
                       @RequestParam String imagePath,
                       Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(adService.addAd(createOrUpdateAdDTO, imagePath, adsUserDetails.getUser().getUserName()));
    }


    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable int id) {
        return adService.getAds(id);
    }


    // Обновление объявления
    @PatchMapping("/{id}")
    public Ads updateAds(@PathVariable int id, @Valid @RequestBody CreateOrUpdateAd createOrUpdateAdDTO) {
        return adService.updateAd(id, createOrUpdateAdDTO);
    }


    // Удалить объявление
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable int id) {
        adService.removeAd(id);

    }


    //Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public Ads getAdsMe(Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        return adService.getAdsMe(adsUserDetails.getUser().getId());
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public String updateImage(@PathVariable int id, @RequestBody String pathImage) {
        return adService.updateImage(id, pathImage);
    }


}
