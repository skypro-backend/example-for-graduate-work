package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.service.AdsService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;


    /** Получение всех объявлений  */
    @GetMapping()
    public Ads getAllAds(){
        return adsService.getAllAds();
    }

    /** Добавление объявления */
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDTO addAd(@RequestParam("properties") CreateOrUpdateAd createAd,
                       @RequestParam("image") MultipartFile image){
        return adsService.addAd(createAd, image);

    }

    /** Получение информации об объявлении */
    @GetMapping("/{id}")
    public ExtendedAd getAds(@PathVariable int id) {
        return adsService.getAds(id);
    }

    /**  Удаление объявления */
    @DeleteMapping("/{id}")
    public void removeAd (@PathVariable int id){
        adsService.removeAd(id);

    }

    /** Обновление информации об объявлении  */
    @PatchMapping("/{id}")
    public AdDTO updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAd updateAd){
        return adsService.updateAds(id, updateAd);
    }

    /** Получение объявлений авторизованного пользователя */
    @GetMapping("/me")
    public Ads getAdsMe(){
        return adsService.getAdsMe();

    }

    /**    Обновление картинки объявления   */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateImage(@PathVariable int id, @RequestParam("image") MultipartFile image){
        return adsService.updateImage(id, image);
    }

}

