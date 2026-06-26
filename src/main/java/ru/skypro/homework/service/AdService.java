package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

/**
 * Сервис для управления объявлениями.
 * Обеспечивает бизнес-логику создания, обновления, удаления и получения объявлений.
 */

public interface AdService {
    /**
     * Получает список всех существующих объявлений.
     *
     * @return объект {@link Ads}, содержащий количество и список объявлений
     */
    Ads getAllAds();

    /**
     * Добавляет новое объявление в систему.
     *
     * @param properties данные объявления (заголовок, цена, описание)
     * @param image      файл изображения объявления
     * @param username   логин (email) автора объявления
     * @return DTO созданного объявления {@link AdDto}
     */
    AdDto addAd(CreateOrUpdateAd properties, MultipartFile image, String username);
    /**
     * Получает расширенную информацию об объявлении по его идентификатору.
     *
     * @param id уникальный идентификатор объявления
     * @return объект {@link ExtendedAd} с подробными данными объявления и его автора
     */
    ExtendedAd getAd(Integer id);

    /**
     * Удаляет объявление из системы по его идентификатору.
     *
     * @param id уникальный идентификатор удаляемого объявления
     */
    void removeAd(Integer id);

    /**
     * Обновляет текстовую информацию (заголовок, цену, описание) существующего объявления.
     *
     * @param id         уникальный идентификатор обновляемого объявления
     * @param properties новые данные для объявления {@link CreateOrUpdateAd}
     * @return DTO обновленного объявления {@link AdDto}
     */
    AdDto updateAd(Integer id, CreateOrUpdateAd properties);

    /**
     * Получает список всех объявлений, созданных текущим авторизованным пользователем.
     *
     * @param username логин (email) текущего пользователя
     * @return объект {@link Ads} со списком объявлений пользователя
     */
    Ads getAdsMe(String username);

    /**
     * Обновляет или загружает новое изображение для существующего объявления.
     *
     * @param id    уникальный идентификатор объявления
     * @param image файл нового изображения объявления
     */
    void updateAdImage(Integer id, MultipartFile image);
}