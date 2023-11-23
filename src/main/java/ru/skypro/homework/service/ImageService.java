package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Image;

/**
 * Сервис для работы с изображениями
 */
public interface ImageService {

    /**
     * Загрузка изображения
     *
     * @param imagePath Путь к изображению
     * @param file      Файл изображения
     */
    void upload(String imagePath, MultipartFile file);

    /**
     * Удаление изображения с диска
     *
     * @param imagePath Путь к изображению
     */
    void deleteFromDisk(String imagePath);

    /**
     * Удаления изображения
     *
     * @param image Объект изображения
     */
    void delete(Image image);

    /**
     * Редактирование изображения
     *
     * @param image Объект изображения
     * @param file  Файл изображения
     */
    void update(Image image, MultipartFile file);

    /**
     * Скачивание изображения
     *
     * @param imagePath Путь к изображению
     * @return Массив байт
     */
    byte[] download(String imagePath);

    /**
     * Сохранение объекта изображения
     *
     * @param image Объект изображения
     * @return Сохраненный объект изображения
     */
    Image save(Image image);

    /**
     * Создание пути к изображению
     *
     * @param file Файл изображения
     * @return Путь к файлу изображения
     */
    String createImagePath(MultipartFile file);

}
