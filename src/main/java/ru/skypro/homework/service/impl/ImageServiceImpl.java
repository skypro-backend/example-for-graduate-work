package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageService {

    private final String uploadPath;

    public ImageServiceImpl(@Value("${app.image.upload.path}") String uploadPath) {
        this.uploadPath = uploadPath;
        createUploadDirectory();
    }

    private void createUploadDirectory() {
        try {
            Path uploadDirectory = Paths.get(uploadPath);
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать папку для загрузки", e);
        }
    }


    @Override
    public String saveImage(MultipartFile image) {
        try {
            String originalFilename = image.getOriginalFilename();
            assert originalFilename != null;
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID() + extension;

            Path filePath = Paths.get(uploadPath + filename);

            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filename;

        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения файла", e);
        }


    }

    @Override
    public void deleteImage(String filename) {
        try {
            Path filePath = Paths.get(uploadPath + filename);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка удаления файла", e);
        }
    }

    @Override
    public Resource getImage(String filename) {
        try {
            Path filePath = Paths.get(uploadPath + filename);
            if (!filePath.normalize().startsWith(Paths.get(uploadPath).normalize())) {
                throw new RuntimeException("Недопустимый путь к файлу");
            }
            if (!Files.exists(filePath)) {
                throw new RuntimeException("Файл не найден: " + filename);
            }
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        }
    }


}
