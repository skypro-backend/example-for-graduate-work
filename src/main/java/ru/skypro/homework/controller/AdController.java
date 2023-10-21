package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
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
    public AdsDTO getAllAds() {
        return new AdsDTO(1, List.of(new AdDTO(1,"mjrtei/regtr",321,123,"frenhj")));
    }

    //Добавление объявления
    @PostMapping()
    public AdDTO addAd(@RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return adService.addAd(createOrUpdateAdDTO);
    }


    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable int id) {
        return adService.getAds(id);
    }


    // Обновление объявления
    @PatchMapping("/{id}")
    public AdsDTO updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return null;
    }


    // Удалить объявление
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable int id) {
    }


    //Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public AdsDTO getAdsMe() {
        return adService.getAdsMe();
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public String updateImage(@PathVariable int id, @RequestBody String pathImage) {
        return adService.updateImage(id, pathImage);
    }


}
