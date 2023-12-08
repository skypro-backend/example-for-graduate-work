package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
/**
 * Интерфейс, содержащий методы для работы с классом {@link Image}
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
public interface ImageService {

    Image uploadImage(MultipartFile file);


    byte[] getImage(String id);
}
