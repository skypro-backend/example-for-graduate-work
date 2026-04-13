package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.avatars.path:images/avatars/}")
    private String avatarsPath;

    @Value("${images.ads.path:images/ads/}")
    private String adsPath;

    @Override
    public String saveAvatarImage(MultipartFile image, String email) {
        String savedPath = saveImage(image, avatarsPath, email + "_" + System.currentTimeMillis());
        return "/" + savedPath;
    }

    @Override
    public String saveAdImage(MultipartFile image, Integer adId) {
        String savedPath = saveImage(image, adsPath, "ad_" + adId + "_" + System.currentTimeMillis());
        return "/" + savedPath;
    }

    @Override
    public String saveImage(MultipartFile image, String directory, String baseName) {
        try {
            Path uploadPath = Paths.get(directory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("Created directory: {}", directory);
            }

            String originalFilename = image.getOriginalFilename();
            String extension = getFileExtension(originalFilename);

            String fileName = baseName + extension;
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, image.getBytes());
            log.info("Image saved to: {}", filePath);

            return directory + fileName;

        } catch (IOException e) {
            log.error("Failed to save image", e);
            throw new RuntimeException("Не удалось сохранить изображение", e);
        }
    }

    @Override
    public byte[] getImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }

        try {
            String cleanPath = imagePath.startsWith("/") ? imagePath.substring(1) : imagePath;
            Path path = Paths.get(cleanPath);

            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            } else {
                log.warn("Image not found: {}", cleanPath);
                return null;
            }
        } catch (IOException e) {
            log.error("Failed to read image: {}", imagePath, e);
            return null;
        }
    }

    @Override
    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return;
        }

        try {
            String cleanPath = imagePath.startsWith("/") ? imagePath.substring(1) : imagePath;
            Path path = Paths.get(cleanPath);
            if (Files.exists(path)) {
                Files.delete(path);
                log.info("Deleted image: {}", cleanPath);
            }
        } catch (IOException e) {
            log.error("Failed to delete image: {}", imagePath, e);
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return ".jpg";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}