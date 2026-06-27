package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Сервис управления медиафайлами (изображениями).
 * Обеспечивает сохранение картинок на жесткий диск и их чтение.
 */
@Service
public class ImageService {

    @Value("${app.images.dir}")
    private String imagesDir;

    /**
     * Сохраняет файл изображения на диск с генерацией уникального имени.
     * Создает целевую директорию, если она отсутствует.
     *
     * @param image файл изображения в формате {@link MultipartFile}
     * @return уникальное сгенерированное имя сохраненного файла
     * @throws IOException в случае системных ошибок записи файла на диск
     */
    public String saveImage(MultipartFile image) throws IOException {
        Path filePath = Paths.get(imagesDir);
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = filePath.resolve(fileName);

        Files.write(path, image.getBytes());
        return fileName;
    }

    /**
     * Считывает байтовое представление изображения с жесткого диска.
     *
     * @param fileName имя файла изображения для чтения
     * @return массив байтов изображения
     * @throws IOException в случае ошибок чтения файла (например, если файл не найден)
     */
    public byte[] getImageBytes(String fileName) throws IOException {
        Path path = Paths.get(imagesDir).resolve(fileName);
        return Files.readAllBytes(path);
    }
}