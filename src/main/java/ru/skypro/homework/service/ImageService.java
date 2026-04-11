package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс для работы с изображениями.
 * Включает сохранение, удаление и получение изображений.
 */
public interface ImageService {

    /**
     * Сохраняет изображение и возвращает URL для доступа
     * @param file изображение
     * @return URL вида /images/{uuid}
     */
    String saveImage(MultipartFile file);

    /**
     * Удаляет изображение по URL
     * @param imageUrl URL изображения
     */
    void deleteImage(String imageUrl);

    /**
     * Получает изображение по имени файла
     * @param filename имя файла (UUID с расширением)
     * @return байты изображения
     */
    byte[] getImage(String filename);
}