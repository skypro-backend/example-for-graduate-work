package com.skypro.controller;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * REST-контроллер для раздачи изображений (картинок объявлений и аватарок).
 * <p>
 * Предоставляет эндпоинты для получения файлов из папок {@code ads/} и {@code users/}.
 * Определяет MIME-тип на основе расширения файла.
 * </p>
 * <p>
 * <b>Примечание:</b> этот контроллер дублирует resource handlers из {@code WebConfig}.
 * Рекомендуется оставить только один механизм раздачи статики.
 * </p>
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    private final String adsDir;
    private final String usersDir;

    public ImageController(@Value("${app.image.upload-path:./uploads/}") String uploadPath) {
        this.adsDir = uploadPath + "ads/";
        this.usersDir = uploadPath + "users/";
    }

    /**
     * Возвращает изображение объявления по имени файла.
     *
     * @param filename имя файла
     * @return изображение с правильным Content-Type или статус 404, если файл не найден
     */
    @GetMapping("/ads/{filename}")
    public ResponseEntity<byte[]> getAdImage(@PathVariable String filename) {
        return readImage(adsDir, filename);
    }

    /**
     * Возвращает аватарку пользователя по имени файла.
     *
     * @param filename имя файла
     * @return изображение с правильным Content-Type или статус 404, если файл не найден
     */
    @GetMapping("/users/{filename}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable String filename) {
        return readImage(usersDir, filename);
    }

    /**
     * Читает изображение из указанной директории и возвращает его в виде байтового массива.
     *
     * @param directory директория, в которой находится файл
     * @param filename  имя файла
     * @return изображение с правильным Content-Type или статус 404/500 при ошибке
     */
    private ResponseEntity<byte[]> readImage(String directory, String filename) {
        Path path = Paths.get(directory, filename).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }
        try {
            MediaType mediaType = resolveMediaType(filename);
            return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(path));
        } catch (IOException e) {
            log.error("Failed to read image: {}", path, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Определяет MIME-тип файла по его расширению.
     *
     * @param filename имя файла
     * @return соответствующий {@link MediaType} (по умолчанию {@code IMAGE_JPEG})
     */
    private MediaType resolveMediaType(String filename) {
        String name = filename.toLowerCase();
        if (name.endsWith(".png")) return MediaType.IMAGE_PNG;
        if (name.endsWith(".gif")) return MediaType.IMAGE_GIF;
        if (name.endsWith(".bmp") || name.endsWith(".webp")) return MediaType.valueOf("image/" + name.substring(name.lastIndexOf('.') + 1));
        return MediaType.IMAGE_JPEG;
    }
}