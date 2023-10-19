package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdServiceImpl adService;

//    public AdController(AdServiceImpl adService) {
//        this.adService = adService;
//    }


    //    Получение всех объявлений
    @GetMapping()
    public AdsDTO getAllAds() {
        return adService.getAllAds();
    }

    //Добавление объявления
    @PostMapping()
    public AdDTO addAd(@RequestBody CreateOrUpdateAd createAd,
                       @RequestParam String imagePath) {
        return adService.addAd(createAd, imagePath);
    }


    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable int id) {
        return adService.getAdFullInfo(id);
    }


    // Удалить объявление
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable int id) {
        adService.deleteAd(id);

    }

    // Обновление объявления
    @PatchMapping("/{id}")
    public AdsDTO updateAds(@PathVariable int id,
                            @RequestBody CreateOrUpdateAd updateAd) {
        return adService.updateAd(updateAd, id);
    }


    //Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public AdsDTO getAdsMe() {
        return adService.getUserAdds();
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public String updateImage(@PathVariable int id) {
        return adService.updateImage();
    }


}
