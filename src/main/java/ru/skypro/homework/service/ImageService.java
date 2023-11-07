package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {

    /**
     * Сохранение изображения в базу данных
     * image файл изображения, не может быть {@code null}.
     */
    Image downloadImage(MultipartFile image) throws IOException;

    /**
     * Получение изображения из базы данных
     * id - идентификатор изображения, не может быть {@code null}.
     */
    byte[] getImage(Long id);

}
