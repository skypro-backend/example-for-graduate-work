package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.FileStorageException;
import ru.skypro.homework.service.FileStorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Сервис хранения файлов (изображений пользователей и объявлений).
 * Отвечает за:
 * - сохранение файлов на диск,
 * - получение файлов по пути.
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path getRoot() {
        return Paths.get(uploadDir);
    }

    /**
     * Сохраняет изображение пользователя.
     */
    @Override
    public String saveUserImage(Integer id, MultipartFile image) {
        return save(image, "users/" + id);
    }

    private String save(MultipartFile image, String folder) {
        try {
            Path root = Paths.get(uploadDir);
            Path directory = root.resolve(folder);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            String fileName = image.getOriginalFilename();
            Path filePath = directory.resolve(fileName);
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return folder + "/" + fileName;
        } catch (IOException exception) {
            throw new FileStorageException("Error saving file");
        }
    }

    /**
     * Сохраняет изображение объявления.
     */
    @Override
    public String saveAdImage(Integer id, MultipartFile image) {
        return save(image, "ads/" + id);
    }

    /**
     * Загружает файл изображения по пути.
     * @return массив байт изображения
     */
    @Override
    public byte[] getImage(String path) {
        try {
            String cleanPath = path.startsWith("/")
                    ? path.substring(1)
                    : path;
            Path filePath = Paths.get(uploadDir).resolve(cleanPath);
            return Files.readAllBytes(filePath);
        } catch (IOException exception) {
            throw new FileStorageException("Error reading file");
        }
    }
}
