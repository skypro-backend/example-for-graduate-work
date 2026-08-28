package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.StorageException;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Value("${app.images.upload-path}")
    private String uploadPath;

    @Value("${app.images.base-url}")
    private String baseUrl;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        this.rootLocation = Paths.get(uploadPath);
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
                log.info("Created upload directory: {}", rootLocation.toAbsolutePath());
            }
            log.info("Upload directory exists: {}", Files.exists(rootLocation));
            log.info("Upload directory path: {}", rootLocation.toAbsolutePath());
        } catch (IOException e) {
            log.error("Could not create upload directory", e);
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public String saveImage(MultipartFile file, String subDirectory, String fileName) {
        log.info("=== SAVING IMAGE ===");
        log.info("Original filename: {}", file.getOriginalFilename());
        log.info("Subdirectory: {}", subDirectory);
        log.info("FileName: {}", fileName);
        log.info("File size: {} bytes", file.getSize());
        log.info("Content type: {}", file.getContentType());

        try {
            // Создаем путь к директории
            Path directory = rootLocation.resolve(subDirectory);
            log.info("Full directory path: {}", directory.toAbsolutePath());

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
                log.info("Created subdirectory: {}", directory.toAbsolutePath());
            }

            // Определяем расширение файла
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // Если расширение не определено, используем .jpg по умолчанию
            if (extension.isEmpty()) {
                extension = ".jpg";
            }

            String fullFileName = fileName + extension;
            Path filePath = directory.resolve(fullFileName);
            log.info("Full file path: {}", filePath.toAbsolutePath());

            // Сохраняем файл
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("Image saved successfully to: {}", filePath.toAbsolutePath());

            // Возвращаем относительный путь
            String relativePath = subDirectory + "/" + fullFileName;
            log.info("Returning relative path: {}", relativePath);
            return relativePath;

        } catch (IOException e) {
            log.error("Error saving image", e);
            throw new StorageException("Failed to save image: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] getImage(String filePath) {
        log.debug("Getting image from path: {}", filePath);

        try {
            if (filePath == null || filePath.isEmpty()) {
                log.warn("Image path is null or empty");
                return null;
            }

            Path path = rootLocation.resolve(filePath);
            log.debug("Resolved path: {}", path.toAbsolutePath());

            if (!Files.exists(path)) {
                log.warn("Image not found at: {}", path.toAbsolutePath());
                return null;
            }

            byte[] imageData = Files.readAllBytes(path);
            log.debug("Image loaded, size: {} bytes", imageData.length);
            return imageData;

        } catch (IOException e) {
            log.error("Error reading image from: {}", filePath, e);
            return null;
        }
    }

    @Override
    public void deleteImage(String filePath) {
        log.debug("Deleting image from path: {}", filePath);

        try {
            if (filePath == null || filePath.isEmpty()) {
                return;
            }

            Path path = rootLocation.resolve(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
                log.debug("Image deleted: {}", path.toAbsolutePath());
            } else {
                log.warn("Image not found for deletion: {}", path.toAbsolutePath());
            }
        } catch (IOException e) {
            log.error("Error deleting image from: {}", filePath, e);
        }
    }

    @Override
    public String getImageUrl(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        String url = "http://localhost:8080/images/" + filePath;
        log.debug("Generated image URL: {}", url);
        return url;
    }
}
