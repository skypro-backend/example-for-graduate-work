package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.impl.AdServiceImpl;

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
        return adService.getAllAds();
    }

    //Добавление объявления
    @PostMapping()
    public AdDTO addAd() {
        return adService.addAd();
    }


    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ExtendedAd getAdInfo(@PathVariable int id) {
        return adService.getAdFullInfo(id);
    }


    // Обновление объявления
    @PatchMapping("/{id}")
    public AdsDTO updateAd(@PathVariable int id) {
        return null;
    }


    // Удалить объявление
    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable int id) {
    }


    //Получение объявлений авторизованного пользователя
    @GetMapping("/me}")
    public AdsDTO getUserAds() {
        return adService.getUserAdds();
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public String updateImage(@PathVariable int id) {
        return adService.updateImage();
    }


}
