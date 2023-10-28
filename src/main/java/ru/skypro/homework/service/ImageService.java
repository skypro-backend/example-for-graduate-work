package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {

    /**
     * Получение изображения по идентификатору
     *
     * @param id идентификатор файла
     * @return массив byte картинки
     */
    byte[] getImageInByteById(int id);

    /**
     * Обновление изображения
     *
     * @param file  {@link MultipartFile}
     * @param image {@link Image}
     * @return объект {@link Image}
     */
    Image updateImage(MultipartFile file, Image image);

}