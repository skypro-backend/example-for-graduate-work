package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

/**
 * Сервис для работы с объявлениями: создание, чтение, обновление, удаление,
 * а также загрузка картинки объявления.
 */

public interface AdService {

    /**
     * Возвращает все объявления.
     */

    Ads getAllAds();

    /**
     * Создаёт новое объявление от имени текущего пользователя.
     */

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);

    /**
     * Возвращает объявления текущего пользователя.
     */

    Ads getAdsMe(Authentication authentication);

    /**
     * Возвращает подробную информацию об объявлении по id.
     */

    ExtendedAd getAds(Integer id);

    /**
     * Удаляет объявление.
     *
     * @throws org.springframework.security.access.AccessDeniedException если текущий пользователь не автор объявления и не ADMIN
     */

    void removeAd(Integer id, Authentication authentication);

    /**
     * Обновляет данные объявления.
     *
     * @throws org.springframework.security.access.AccessDeniedException если текущий пользователь не автор объявления и не ADMIN
     */

    Ad updateAds(Integer id, CreateOrUpdateAd dto, Authentication authentication);

    /**
     * Обновляет картинку объявления и возвращает её байты.
     *
     * @throws org.springframework.security.access.AccessDeniedException если текущий пользователь не автор объявления и не ADMIN
     */

    byte[] updateImage(Integer id, MultipartFile image, Authentication authentication);
}
