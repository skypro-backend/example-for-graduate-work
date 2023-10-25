package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;
/**
 * Контроллер для работы с картинками
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {
    /**
 * Сервис для работы с объявлением
 */
    private final AdService adService;
    /**
     * Сервис для работы с картинками
     */
    private final ImageService imageService;
    /**
     * Конструктор - создание нового объекта
     */
    public ImageController(AdService adService, ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }
    /**
     * Метод по передаче картинок
     */
    @GetMapping(value = "/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage() {
        // тут пишем код, который вытаскивает entity из БД
        return entity.getImage();
        /**
         * Метод по сохранению картинки в БД
         */

    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile image) {
        Image entity = new Image();
        entity.setId(UUID.randomUUID().toString()); // генерируем уникальный идентификатор
        try {
            byte[] bytes = file.getBytes();
            entity.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity = ImageRepository.save(entity);
        return entity.getId();
    }

}
