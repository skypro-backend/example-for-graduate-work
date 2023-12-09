package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

/**
 * Сервис для работы с объявлениями
 */
public interface AdService {

    /**
     * Получение всех объявлений (без фильтров)
     *
     * @return ДТО списка объявлений
     */
    AdsDto getAllAds();

    /**
     * Добавление объявления и изображения к нему
     *
     * @param file Файл с изображением
     * @param dto  ДТО объявления
     * @return ДТО созданного объявления
     */
    AdDto addAd(MultipartFile file, CreateOrUpdateAdDto dto);

    /**
     * Получение объявления по id
     *
     * @param id id объявления
     * @return Расширенное ДТО объявления
     */
    ExtendedAdDto getAd(Integer id);

    /**
     * Удаление объявления
     *
     * @param id id объявления
     */
    void removeAd(Integer id);

    /**
     * Редактирование объявления
     *
     * @param id  id объявления
     * @param dto ДТО редактируемого объявления
     * @return ДТО отредактированного объявления
     */
    AdDto updateAd(Integer id, CreateOrUpdateAdDto dto);

    /**
     * Получение объявлений по текущему пользователю
     *
     * @return ДТО списка объявлений
     */
    AdsDto getAdsMe();

    /**
     * Редактирование изображения к объявлению
     *
     * @param id   id объявления
     * @param file Файл с изображением
     * @return Массив байт
     */
    byte[] updateAdImage(Integer id, MultipartFile file);

    /**
     * Получение изображения по id
     *
     * @param id id объявления
     * @return Массив байт
     */
    byte[] getImage(Integer id);

    /**
     * Проверка наличия объявления по id и логину пользователя
     *
     * @param id       id объявления
     * @param username Логин пользователя
     * @return Объявление существует или нет
     */
    boolean existByAdIdAndUsername(Integer id, String username);

}
