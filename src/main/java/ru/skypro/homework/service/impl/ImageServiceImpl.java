package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        } catch (IOException e) {
            log.error("Could not create upload directory", e);
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public String saveImage(MultipartFile file, String subDirectory, String fileName) {
        log.debug("Saving image: {}, subDirectory: {}, fileName: {}", file.getOriginalFilename(), subDirectory, fileName);

        try {
            // Создаем путь к директории
            Path directory = rootLocation.resolve(subDirectory);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Определяем расширение файла
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // Если имя файла не указано, генерируем его
            if (fileName == null || fileName.isEmpty()) {
                fileName = UUID.randomUUID().toString();
            }

            String fullFileName = fileName + extension;
            Path filePath = directory.resolve(fullFileName);

            // Сохраняем файл
            file.transferTo(filePath.toFile());
            log.debug("Image saved to: {}", filePath.toAbsolutePath());

            // Возвращаем относительный путь
            return subDirectory + "/" + fullFileName;

        } catch (IOException e) {
            log.error("Error saving image", e);
            throw new RuntimeException("Failed to save image", e);
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
            if (!Files.exists(path)) {
                log.warn("Image not found at: {}", path.toAbsolutePath());
                return null;
            }

            return Files.readAllBytes(path);

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
        // Возвращаем URL для доступа к картинке через контроллер
        return baseUrl + filePath;
    }
}
