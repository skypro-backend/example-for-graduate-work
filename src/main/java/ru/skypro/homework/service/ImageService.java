package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile imageFile);

    /**
     * Удаление изображения из БД.
     * @param image - объект изображения
     */

    void removeImage(Image image);

    /**
     * Получаем изображение из БД
     * @param id - ID изображения
     * @return Image объект изображения
     */

    Image getImage(Long id);
}
