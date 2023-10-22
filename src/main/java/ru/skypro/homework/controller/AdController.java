package ru.skypro.homework.controller;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;

import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdServiceImpl adService;

    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }


    //    Получение всех объявлений
    @GetMapping()
    public Ads getAllAds() {
        return new Ads(1, List.of(new AdDTO(1, "mjrtei/regtr", 321, 123, "frenhj")));
    }

    //Добавление объявления
    @PostMapping()
    public AdDTO addAd(@RequestBody CreateOrUpdateAd createOrUpdateAdDTO, @RequestParam String imagePath) {
        return adService.addAd(createOrUpdateAdDTO, imagePath);
    }


    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable int id) {
        return adService.getAds(id);
    }


    // Обновление объявления
    @PatchMapping("/{id}")
    public Ads updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAd createOrUpdateAdDTO) {
        return adService.updateAd(id, createOrUpdateAdDTO);
    }


    // Удалить объявление
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable int id) {
    }


    //Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public Ads getAdsMe() {
        return adService.getAdsMe();
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public String updateImage(@PathVariable int id, @RequestBody String pathImage) {
        return adService.updateImage(id, pathImage);
    }


}
