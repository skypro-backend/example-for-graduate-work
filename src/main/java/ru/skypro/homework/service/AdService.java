package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import java.nio.file.AccessDeniedException;

public interface AdService {

    /**
     * Получает все объявления. Права имеют все пользователи.
     * @return AdsDto - dto для передачи полного списка объявлений
     */
    AdsDTO getAllAds();
    /**
     * Добавление объявления. Права имеет только аутентифицированный пользователь.
     * @param createOrUpdateAdDTO - dto для создания объявления
     * @param image - dto для добавления изображения во время создания объявления
     * @param authentication пользователь авторизованной сессии
     * @return AdDto - dto объявления
     */
    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image, Authentication authentication);

    /**
     * Получение информации об объявлении. Права имеет только аутентифицированный пользователь.
     * @param id ID объявления
     * @return ExtendedAdDto - dto для получении
     */

    ExtendedAdDTO getAd(Long id);

    /**
     * Удаление объявления. Права имеют хозяин объявления и пользователь с ролью ADMIN.
     * @param id ID объявления
     * @param authentication пользователь авторизованной сессии
     */

    void deleteAd(Long id, Authentication authentication);

    /**
     * Обновление информации об объявлении. Права имеют хозяин объявления и пользователь с ролью ADMIN.
     * @param id ID объявления
     * @param createOrUpdateAdDTO - dto для обновления объявления
     * @param authentication пользователь авторизованной сессии
     * @return AdDto - dto с обновленным объявлением
     */

    AdDTO updateAd(Long id, CreateOrUpdateAdDTO createOrUpdateAdDTO, Authentication authentication);

    /**
     * Получение объявлений авторизованного пользователя. Права имеет только аутентифицированный пользователь.
     * @param authentication пользователь авторизованной сессии
     * @return AdsDto - dto для передачи полного списка объявлений
     */

    AdsDTO getAdsMe(Authentication authentication);

    /**
     * Обновление изображения объявления. Права имеют хозяин объявления и пользователь с ролью ADMIN.
     * @param id ID объявления
     * @param image изображение объявления
     * @param authentication пользователь авторизованной сессии
     */

    void updateAdImage(Long id, MultipartFile image, Authentication authentication);


}
