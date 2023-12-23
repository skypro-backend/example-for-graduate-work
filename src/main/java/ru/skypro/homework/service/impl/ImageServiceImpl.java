package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    //Метод получения изображения по ID
    @Override
    public ResponseEntity<byte[]> getImage(Integer id) {
        log.info("Getting image by ID: {}", id);
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);   // Поиск изображения в репозитории по его ID
        byte[] imageBytes = image.getData();                                                   // Получение байтов данных изображения
        HttpHeaders headers = new HttpHeaders();                                              // Создание HttpHeaders для установки метаданных изображения в HTTP-ответе
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));               // Установка типа контента (медиа-типа) изображения в HTTP-ответе на основе медиа-типа, полученного из объекта Image
        headers.setContentLength(imageBytes.length);                                         // Установка длины контента в HTTP-ответе на основе размера байтов данных изображения
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageBytes);       // Возвращение HTTP-ответа с байтами данных изображения и установленными метаданными
    }

    //Метод добавления изображения
    @Override
    public Image addImage(MultipartFile image) {
        log.info("Create and update image");
        Image newImage = new Image();                      // Создание нового объекта Image
        try {
            newImage.setData(image.getBytes());                // Установка байтов данных изображения
            newImage.setMediaType(image.getContentType());    // Установка медиа-типа изображения
            newImage.setFileSize(image.getSize());           // Установка размера файла изображения
        } catch (IOException e) {
            throw new ForbiddenException(e);      // В случае ошибки ввода/вывода при чтении байтов данных изображения
                                                 // выбрасывается исключение ForbiddenException
        }
        imageRepository.save(newImage);           // Сохранение нового изображения в репозитории
        return newImage;                         // Возвращение нового изображения
    }

    //Метод удаления изображения
    @Override
    public void deleteImage(Integer imageId) {
        log.info("Delete image");
        imageRepository.deleteById(imageId);    // Удаление изображения из репозитория по его ID
    }
}
