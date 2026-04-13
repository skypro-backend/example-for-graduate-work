package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.Optional;

/**
 * Сервис для управления объявлениями
 * <p>
 * Предоставляет методы для операций с объявлениями,
 * загрузки изображений и проверки прав доступа
 * </p>
 */
public interface AdService {

    /**
     * Возвращает все объявления в системе
     *
     * @return объект Ads, содержащий список всех объявлений и их количество
     */
    Ads getAllAds();

    /**
     * Возвращает объявление по его идентификатору
     *
     * @param id идентификатор объявления
     * @return Optional с расширенной информацией об объявлении или пустой Optional
     */
    Optional<ExtendedAd> getAdById(Integer id);

    /**
     * Создает новое объявление
     *
     * @param createAd  DTO с данными для создания объявления
     * @param userEmail email автора (из контекста безопасности)
     * @return Optional с созданным объявлением
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    Optional<Ad> createAd(CreateOrUpdateAd createAd, String userEmail);

    /**
     * Обновляет существующее объявление
     *
     * @param id        идентификатор объявления
     * @param updateAd  DTO с обновленными данными
     * @return Optional с обновленным объявлением
     * @throws RuntimeException если объявление не найдено
     */
    Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateAd);

    /**
     * Возвращает объявления авторизованного пользователя
     *
     * @param email email пользователя
     * @return объект Ads со списком объявлений пользователя
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    Ads getAdsByUserEmail(String email);

    /**
     * Удаляет объявление по идентификатору
     *
     * @param id идентификатор объявления
     * @return true если объявление успешно удалено, false если не найдено
     */
    boolean deleteAd(Integer id);

    /**
     * Обновляет изображение объявления
     *
     * @param adId  идентификатор объявления
     * @param image файл изображения
     * @return путь к сохраненному изображению
     * @throws RuntimeException если объявление не найдено или не удалось сохранить изображение
     */
    String updateAdImage(Integer adId, MultipartFile image);

    /**
     * Получает изображение объявления
     *
     * @param adId идентификатор объявления
     * @return массив байтов изображения или null, если изображение не найдено
     * @throws RuntimeException если объявление не найдено
     */
    byte[] getAdImage(Integer adId);

    /**
     * Проверяет, является ли пользователь владельцем объявления
     *
     * @param adId  идентификатор объявления
     * @param email email пользователя
     * @return true если пользователь является владельцем, false в противном случае
     */
    boolean isAdOwner(Integer adId, String email);

    /**
     * Проверяет, имеет ли пользователь роль администратора
     *
     * @param email email пользователя
     * @return true если пользователь администратор, false в противном случае
     */
    boolean isAdmin(String email);
}