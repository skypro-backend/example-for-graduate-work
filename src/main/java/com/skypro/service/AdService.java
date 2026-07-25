package com.skypro.service;

import com.skypro.dto.Ad;
import com.skypro.dto.Ads;
import com.skypro.dto.CreateOrUpdateAd;
import com.skypro.dto.ExtendedAd;
import com.skypro.exception.AdNotFoundException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для управления объявлениями.
 * <p>
 * Определяет контракт для операций с объявлениями:
 * получение, создание, обновление, удаление, а также работа с изображениями.
 * </p>
 */
public interface AdService {

    /**
     * Возвращает список всех объявлений.
     *
     * @return объект {@link Ads} с количеством и списком объявлений
     */
    Ads getAllAds();

    /**
     * Создаёт новое объявление от имени указанного пользователя.
     *
     * @param createOrUpdateAd данные объявления (заголовок, цена, описание)
     * @param image            файл изображения
     * @param username         логин автора (из аутентификации)
     * @return созданное объявление в виде {@link Ad}
     */
    Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);

    /**
     * Возвращает детальную информацию об объявлении по его идентификатору.
     *
     * @param id идентификатор объявления
     * @return объект {@link ExtendedAd} с полной информацией
     * @throws AdNotFoundException если объявление не найдено
     */
    ExtendedAd getAd(Integer id);

    /**
     * Обновляет данные существующего объявления.
     * <p>
     * Доступно только владельцу или администратору.
     * </p>
     *
     * @param id               идентификатор объявления
     * @param createOrUpdateAd новые данные
     * @return обновлённое объявление в виде {@link Ad}
     * @throws AdNotFoundException если объявление не найдено
     */
    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd);

    /**
     * Удаляет объявление по идентификатору.
     * <p>
     * Доступно только владельцу или администратору.
     * </p>
     *
     * @param id идентификатор объявления
     */
    void removeAd(Integer id);

    /**
     * Возвращает список объявлений, созданных текущим пользователем.
     *
     * @param username логин пользователя
     * @return объект {@link Ads} с объявлениями пользователя
     */
    Ads getAdsMe(String username);

    /**
     * Обновляет изображение объявления.
     * <p>
     * Доступно только владельцу или администратору.
     * </p>
     *
     * @param id    идентификатор объявления
     * @param image новый файл изображения
     */
    void updateImage(Integer id, MultipartFile image);
}