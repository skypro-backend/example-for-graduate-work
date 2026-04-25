package ru.skypro.homework.service.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageUtil {


    private static final String uploadDir = "uploads/images/";

    public static String saveImage (MultipartFile image){
        try {
            String title = image.getOriginalFilename();
            // Создаем уникальное имя файла
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;

            // Создаем полный путь
            Path uploadPath = Paths.get(uploadDir);
            Path filePath = uploadPath.resolve(filename);

            // Создаем директорию, если её нет
            Files.createDirectories(uploadPath);

            // Сохраняем файл
            Files.write(filePath, image.getBytes());

            // Возвращаем путь
            return "/uploads/ads/" + filename;
        } catch (Exception e) {
            throw new RuntimeException("не удалось сохранить изображение");
        }
    }

    public static void deleteImage(String imagePath) {
        try {
            Files.deleteIfExists(Paths.get(imagePath));
        } catch (Exception e) {
            throw new RuntimeException("не удалось удалить изображение");
        }
    }
}
