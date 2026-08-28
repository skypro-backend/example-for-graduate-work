package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для управления объявлениями.
 * Предоставляет методы для создания, получения, обновления и удаления объявлений.
 */
public interface AdService {

    /**
     * Получает список всех объявлений.
     *
     * @return DTO со списком всех объявлений
     */
    Ads getAllAds();

    /**
     * Создает новое объявление.
     *
     * @param createOrUpdateAd DTO с данными объявления
     * @param image файл с изображением объявления
     * @param username имя автора объявления
     * @return DTO созданного объявления
     * @throws ru.skypro.homework.exception.NotFoundException если пользователь не найден
     * @throws ru.skypro.homework.exception.StorageException если ошибка сохранения файла
     */
    Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);

    /**
     * Получает детальную информацию об объявлении по его ID.
     *
     * @param id ID объявления
     * @return DTO с расширенной информацией об объявлении
     * @throws ru.skypro.homework.exception.NotFoundException если объявление не найдено
     */
    ExtendedAd getAd(Integer id);

    /**
     * Удаляет объявление по его ID.
     *
     * @param id ID объявления
     * @param username имя пользователя, выполняющего операцию
     * @throws ru.skypro.homework.exception.NotFoundException если объявление не найдено
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является автором или администратором
     */
    void removeAd(Integer id, String username);

    /**
     * Обновляет информацию об объявлении.
     *
     * @param id ID объявления
     * @param createOrUpdateAd DTO с обновленными данными
     * @param username имя пользователя, выполняющего операцию
     * @return DTO обновленного объявления
     * @throws ru.skypro.homework.exception.NotFoundException если объявление не найдено
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является автором или администратором
     */
    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username);

    /**
     * Получает список объявлений авторизованного пользователя.
     *
     * @param username имя пользователя
     * @return DTO со списком объявлений пользователя
     * @throws ru.skypro.homework.exception.NotFoundException если пользователь не найден
     */
    Ads getAdsMe(String username);

    /**
     * Обновляет изображение объявления и возвращает бинарные данные.
     *
     * @param id ID объявления
     * @param image файл с новым изображением
     * @param username имя пользователя, выполняющего операцию
     * @return массив байтов изображения
     * @throws ru.skypro.homework.exception.NotFoundException если объявление не найдено
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является автором или администратором
     * @throws ru.skypro.homework.exception.StorageException если ошибка сохранения файла
     */
    byte[] updateImage(Integer id, MultipartFile image, String username);

    /**
     * Проверяет, является ли пользователь автором объявления или администратором.
     *
     * @param username имя пользователя
     * @param adId ID объявления
     * @return true, если пользователь является автором или администратором
     */
    boolean isAuthor(String username, Integer adId);
}
