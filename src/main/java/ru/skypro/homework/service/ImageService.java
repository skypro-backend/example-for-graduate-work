package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

/**
 * Интерфейс для работы с аватарками пользователя
 */
public interface ImageService {

      Image uploadImage (MultipartFile multipartFile) throws IOException; // Сохранение изображения в базу данных

      byte[] getImage (Integer imageId); // Получение изображения из базы данных
}
