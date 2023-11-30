package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.util.UUID;

/**
 * Класс-сервис, реализующий интерфейс {@link ImageService}
 */
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {


    private final ImageRepository imageRepository;
    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    /**
     * Метод для загрузки изображение
     * @param file
     * @return {@link Image}
     */
    @Override
    public Image uploadImage(MultipartFile file) {
        Image image = new Image();
        image.setId((UUID.randomUUID().toString())); // генерируем уникальный идентификатор
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageRepository.save(image);

        logger.info("Изображение сохраненно в базе данных", image);
        return image;
    }

    /**
     * Метод для получения изображения из базы данных
     * @param id
     * @return byte[]
     */
    @Override
    public byte[] getImage(String id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);

        return image.getImage();
    }

}
