package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {

    /** Получение изображения по названию файла
     * @param fileName название файла {@link String}
     * @return массив byte картинки */
    byte[] getImageInByte(String fileName);

    /** Изменение картинки пользователя
     * @param userId id пользователя {@link Long}
     * @param imageId id картинки {@link Long}
     * @param multipartFile объект {@link  MultipartFile}
     * @return объект {@link Image} */
    Image changeUserImage(Long userId, Long imageId, MultipartFile multipartFile) throws IOException;

    /** Изменение картинки объявления
     * @param idAd id объявления {@link Long}
     * @param imageId id картинки {@link Long}
     * @param multipartFile объект {@link  MultipartFile}
     * @return объект {@link Image} */
    Image changeAdImage(Long idAd, Long imageId, MultipartFile multipartFile) throws IOException;



}