package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

/**
 * Контроллер для управления объявлениями.
 * Предоставляет REST API для создания, получения, обновления и удаления объявлений.
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    private static final Logger log = LoggerFactory.getLogger(AdController.class);
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    /**
     * Получает список всех объявлений.
     * Доступен без авторизации.
     *
     * @return ResponseEntity со списком объявлений
     */
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        log.debug("Get all ads request");
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Создает новое объявление.
     * Требуется авторизация с ролью USER или ADMIN.
     *
     * @param properties данные объявления
     * @param image изображение объявления
     * @param authentication объект аутентификации
     * @return ResponseEntity с созданным объявлением
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Ad> addAd(
            @RequestParam("properties") CreateOrUpdateAd properties,
            @RequestParam("image") MultipartFile image,
            Authentication authentication) {
        log.debug("Add ad request by user: {}", authentication.getName());
        Ad ad = adService.addAd(properties, image, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    /**
     * Получает детальную информацию об объявлении по ID.
     * Доступен без авторизации.
     *
     * @param id ID объявления
     * @return ResponseEntity с детальной информацией об объявлении
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        log.debug("Get ad by id: {}", id);
        return ResponseEntity.ok(adService.getAd(id));
    }

    /**
     * Удаляет объявление по ID.
     * Требуется быть автором объявления или иметь роль ADMIN.
     *
     * @param id ID объявления
     * @param authentication объект аутентификации
     * @return ResponseEntity с кодом 204 No Content
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @adService.isAuthor(authentication.name, #id)")
    public ResponseEntity<?> removeAd(@PathVariable Integer id, Authentication authentication) {
        log.debug("Remove ad id: {} by user: {}", id, authentication.getName());
        adService.removeAd(id, authentication.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Обновляет информацию об объявлении.
     * Требуется быть автором объявления или иметь роль ADMIN.
     *
     * @param id ID объявления
     * @param createOrUpdateAd обновленные данные
     * @param authentication объект аутентификации
     * @return ResponseEntity с обновленным объявлением
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @adService.isAuthor(authentication.name, #id)")
    public ResponseEntity<Ad> updateAds(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateAd createOrUpdateAd,
            Authentication authentication) {
        log.debug("Update ad id: {} by user: {}", id, authentication.getName());
        Ad ad = adService.updateAd(id, createOrUpdateAd, authentication.getName());
        return ResponseEntity.ok(ad);
    }

    /**
     * Получает объявления авторизованного пользователя.
     * Требуется авторизация с ролью USER или ADMIN.
     *
     * @param authentication объект аутентификации
     * @return ResponseEntity со списком объявлений пользователя
     */
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        log.debug("Get my ads for user: {}", authentication.getName());
        return ResponseEntity.ok(adService.getAdsMe(authentication.getName()));
    }

    /**
     * Обновляет изображение объявления.
     * Требуется быть автором объявления или иметь роль ADMIN.
     *
     * @param id ID объявления
     * @param image новое изображение
     * @param authentication объект аутентификации
     * @return ResponseEntity с URL загруженного изображения
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN') or @adService.isAuthor(authentication.name, #id)")
    public ResponseEntity<String> updateImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image,
            Authentication authentication) {
        log.debug("Update image for ad id: {} by user: {}", id, authentication.getName());
        String imageUrl = adService.updateImage(id, image, authentication.getName());
        return ResponseEntity.ok(imageUrl);
    }
}