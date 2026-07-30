package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

/**
 * Сервис работы с объявлениями.
 */
public interface AdService {

    /**
     * Возвращает все объявления.
     *
     * @return список объявлений
     */
    Ads getAllAds();

    /**
     * Возвращает полную информацию об объявлении.
     *
     * @param id идентификатор объявления
     * @return полная информация
     */
    ExtendedAd getAd(Long id);

    /**
     * Создаёт объявление.
     *
     * @param username логин автора
     * @param properties данные объявления
     * @param imageBytes изображение
     * @param mediaType MIME-тип изображения
     * @return созданное объявление
     */
    Ad createAd(
            String username,
            CreateOrUpdateAd properties,
            byte[] imageBytes,
            String mediaType
    );

    /**
     * Возвращает объявления текущего пользователя.
     *
     * @param username логин пользователя
     * @return объявления пользователя
     */
    Ads getMyAds(String username);

    /**
     * Обновляет объявление.
     *
     * @param id идентификатор объявления
     * @param username логин пользователя
     * @param role роль пользователя
     * @param properties новые данные
     * @return обновлённое объявление
     */
    Ad updateAd(
            Long id,
            String username,
            String role,
            CreateOrUpdateAd properties
    );

    /**
     * Удаляет объявление.
     *
     * @param id идентификатор
     * @param username логин пользователя
     * @param role роль пользователя
     */
    void deleteAd(
            Long id,
            String username,
            String role
    );

    /**
     * Обновляет картинку объявления.
     *
     * @param id идентификатор объявления
     * @param username логин пользователя
     * @param role роль пользователя
     * @param imageBytes изображение
     * @param mediaType MIME-тип
     */
    void updateImage(
            Long id,
            String username,
            String role,
            byte[] imageBytes,
            String mediaType
    );

    /**
     * Возвращает изображение объявления.
     *
     * @param id идентификатор объявления
     * @return изображение
     */
    byte[] getImage(Long id);

    /**
     * Возвращает MIME-тип изображения.
     *
     * @param id идентификатор объявления
     * @return MIME-тип
     */
    String getImageMediaType(Long id);
}
