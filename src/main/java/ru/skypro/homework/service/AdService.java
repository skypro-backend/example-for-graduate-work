package ru.skypro.homework.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;

public interface AdService {

    /**
     * Получение всех объявлений из базы данных
     */
    AdsDto getAllAds();

    /**
     * Создание нового объявления и сохранение его в базу данных.
     * properties   необходимая информацию о создаваемом объявлении, не может быть {@code null}.
     * image          файл для установки изображения объявления, не может быть {@code null}.
     * authentication объект аутентификации, представляющий текущего пользователя.
     *
     * @throws IOException если возникла ошибка во время сохранения файла изображения.
     */
    AdDto createAd(CreateOrUpdateAdDto properties, MultipartFile image, Authentication authentication) throws IOException;

    /**
     * Обновление изображения объявления и сохранение его в базу данных.
     *
     * @param id идентификатор объявления у которого необходимо обновить изображение, не может быть {@code null}
     *           IOException         если возникла ошибка во время сохранения файла изображения.
     *           AdNotFoundException если объявление по данному {@code id} не было найдено.
     */
    void updateImage(Integer id, MultipartFile image) throws IOException;

    /**
     * Получение объявлений аутентифицированного пользователя из базы данных.
     * authentication объект аутентификации, представляющий текущего пользователя.
     */
    List<AdDto> getAllMyAds(Authentication authentication);

    /**
     * Получение расширенной информации об объявлении по его идентификатору из базы данных.
     *
     * @param id идентификатор объявления, не может быть {@code null}.
     *           AdNotFoundException если объявление по данному {@code id} не было найдено.
     */
    ExtendedAdDto getAdFullInfo(Integer id);

    /**
     * Удаление объявления по его идентификатору из базы данных.
     *
     * @param id идентификатор объявления, не может быть {@code null}.
     *           AdNotFoundException если объявление по данному {@code id} не было найдено.
     */
    void deleteById(Integer id);

    /**
     * Обновление информации объявления по его идентификатору и сохранение в базу данных.
     *
     * @param id идентификатор объявления, не может быть {@code null}.
     *           createOrUpdateAdDto необходимая информация для обновления объявления, не может быть {@code null}.
     *           AdNotFoundException если объявление по данному {@code id} не было найдено.
     */
    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);
}
