package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

/**
 * Интерфейс бизнес-логики для работы с объявлениями.
 */
public interface AdService {
    /**
     * Возвращает список всех существующих в системе объявлений.
     *
     * @return объект {@link Ads}, содержащий список DTO объявлений и их количество
     */
    Ads getAllAds();

    /**
     * Создает и сохраняет новое объявление.
     *
     * @param properties данные объявления (заголовок, цена, описание)
     * @param image файл изображения для объявления
     * @param username имя пользователя (email), создающего объявление
     * @return созданное объявление в виде DTO {@link Ad}
     */
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, String username);

    /**
     * Получает детальную информацию об объявлении по его идентификатору.
     *
     * @param id уникальный идентификатор объявления
     * @return DTO {@link ExtendedAd} с расширенной информацией
     */
    ExtendedAd getAd(Integer id);

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id уникальный идентификатор объявления
     */
    void removeAd(Integer id);

    /**
     * Обновляет текстовые характеристики объявления (заголовок, цена, описание).
     *
     * @param id уникальный идентификатор объявления
     * @param dto новые текстовые данные для объявления
     * @return обновленное объявление в виде DTO {@link Ad}
     */
    Ad updateAd(Integer id, CreateOrUpdateAd dto);

    /**
     * Возвращает список объявлений, созданных текущим авторизованным пользователем.
     *
     * @param username имя пользователя (email)
     * @return объект {@link Ads} со списком объявлений автора
     */
    Ads getAdsMe(String username);

    /**
     * Обновляет файл изображения у существующего объявления.
     *
     * @param id уникальный идентификатор объявления
     * @param image новый файл изображения
     */
    void updateAdImage(Integer id, MultipartFile image);

    /**
     * Проверяет, является ли пользователь автором объявления или администратором.
     * Используется для разграничения доступа на уровне аннотации {@code PreAuthorize}.
     *
     * @param id уникальный идентификатор объявления
     * @param username имя пользователя (email) для проверки прав
     * @return {@code true}, если доступ разрешен, иначе {@code false}
     */
    boolean isAuthorOrAdmin(Integer id, String username);
}