package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ad.Ad;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.security.Principal;
/**
 * Контроллер для работы с объявлениями.
 * Предоставляет API для получения, создания, обновления и удаления объявлений.
 */
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    /**
     * Получает список всех объявлений.
     *
     * @return список объявлений
     */
    @GetMapping
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    /**
     * Создаёт новое объявление.
     *
     * @param request данные объявления
     * @param principal текущий авторизованный пользователь
     * @return созданное объявление
     */
    @PostMapping
    public Ad addAd(@RequestBody CreateOrUpdateAd request,
                    Principal principal) {
        return adService.createAd(request, principal.getName());
    }

    /**
     * Получает расширенную информацию об объявлении.
     *
     * @param id идентификатор объявления
     * @return объявление с расширенной информацией
     */
    @GetMapping("/{id}")
    public ExtendedAd getAd(@PathVariable Integer id) {
        return adService.getExtendedAd(id);
    }

    /**
     * Обновляет объявление.
     *
     * @param id идентификатор объявления
     * @param request новые данные объявления
     * @param principal текущий пользователь
     * @return обновлённое объявление
     */
    @PatchMapping("/{id}")
    public Ad updateAd(@PathVariable Integer id,
                       @RequestBody CreateOrUpdateAd request,
                       Principal principal) {
        return adService.updateAd(id, request, principal.getName());
    }

    /**
     * Удаляет объявление.
     *
     * @param id идентификатор объявления
     * @param principal текущий пользователь
     */
    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Integer id,
                         Principal principal) {
        adService.deleteAd(id, principal.getName());
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @param principal текущий пользователь
     * @return список объявлений пользователя
     */
    @GetMapping("/me")
    public Ads getAdsMe(Principal principal) {
        return adService.getMyAds(principal.getName());
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор объявления
     */
    @PatchMapping("/{id}/image")
    public void updateImage(@PathVariable Integer id) {
        adService.updateImage(id);
    }
}
