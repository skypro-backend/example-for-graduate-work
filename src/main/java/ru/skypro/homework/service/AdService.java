package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {

    /**
     * Создание объявления по id
     *
     * @param properties {@link CreateOrUpdateAd}
     * @param file       {@link MultipartFile}
     * @param username   {@link String}
     * @return объект {@link Ad}
     */
    Ad postAd(CreateOrUpdateAd properties, MultipartFile file, String username);

    /**
     * Получение всех объявлений
     *
     * @return объект {@link Ads}
     */
    Ads getAllAds();

    /**
     * Получение объявления по id
     *
     * @param id {@link Integer}
     * @return объект {@link ExtendedAd}
     */
    ExtendedAd getAdById(int id);

    /**
     * Удаление объявления по id
     *
     * @param id       {@link Integer}
     * @param username {@link String}
     */
    void deleteAdById(int id, String username);

    /**
     * Обновление объявления по id
     *
     * @param id               {@link Integer}
     * @param createOrUpdateAd {@link CreateOrUpdateAd}
     * @param username         {@link String}
     * @return объект {@link Ad}
     */
    Ad patchAdById(int id, CreateOrUpdateAd createOrUpdateAd, String username);

    /**
     * Получение объявлений авторизованного пользователя
     *
     * @param username {@link String}
     * @return объект {@link Ads}
     */
    Ads getMyAds(String username);

    /**
     * Обновление картинки объявления по id
     *
     * @param id       {@link Integer}
     * @param file     {@link MultipartFile}
     * @param username {@link String}
     * @return массив байт
     */
    byte[] patchAdsImageById(int id, MultipartFile file, String username);

}
