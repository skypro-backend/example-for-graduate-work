package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.ApiConstants;
import ru.skypro.homework.exception.ImageStorageException;
import ru.skypro.homework.service.ImageStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${app.images.path}")
    private String imagesDirectory;

    @Override
    public String save(MultipartFile file, String fileNamePrefix) {
        try {
            Path directory = Path.of(imagesDirectory);
            Files.createDirectories(directory);
            String fileName = fileNamePrefix + extractExtension(file.getOriginalFilename());
            Path filePath = directory.resolve(fileName);
            file.transferTo(filePath);
            log.info("Сохранён файл изображения: {}", fileName);
            return ApiConstants.IMAGES_URL_PREFIX + fileName;
        } catch (IOException e) {
            log.error("Не удалось сохранить изображение {}", fileNamePrefix, e);
            throw new ImageStorageException("Не удалось сохранить файл изображения", e);
        }
    }

    @Override
    public byte[] read(String imageUrl) {
        try {
            return Files.readAllBytes(resolveFilePath(imageUrl));
        } catch (IOException e) {
            throw new ImageStorageException("Не удалось прочитать файл изображения", e);
        }
    }

    @Override
    public String detectContentType(String imageUrl) {
        try {
            String contentType = Files.probeContentType(resolveFilePath(imageUrl));
            return contentType != null ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE;
        } catch (IOException e) {
            throw new ImageStorageException("Не удалось определить тип файла изображения", e);
        }
    }

    private Path resolveFilePath(String imageUrl) {
        String fileName = imageUrl.startsWith(ApiConstants.IMAGES_URL_PREFIX)
                ? imageUrl.substring(ApiConstants.IMAGES_URL_PREFIX.length())
                : imageUrl;
        return Path.of(imagesDirectory).resolve(fileName);
    }

    private String extractExtension(String originalFilename) {
        if (originalFilename == null || !originalFilename.contains(".")) {
            return "";
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.'));
    }
}
