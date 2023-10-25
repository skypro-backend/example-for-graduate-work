package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    public static final String USER_DIR = "user.dir";

    private final ImageRepository imageRepository;

    @Override
    public Image updateImage(final MultipartFile file, final Image image) {
        try {
            String fileName = file.getOriginalFilename();
            String projectRoot = System.getProperty(USER_DIR);
            Path path = Paths.get(projectRoot, UPLOAD_DIR);

            try {
                Files.createDirectories(path);
                log.info("Директория успешно создана: " + path);
            } catch (IOException e) {
                log.error("Ошибка при создании директории: " + e.getMessage());
            }

            File imageFile = new File(path + "/" + fileName);
            file.transferTo(imageFile);

            image.setFilePath(imageFile.getAbsolutePath());
            image.setFileSize(file.getSize());
            image.setMediaType(file.getContentType());
            image.setFileName(fileName);
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update file: " + e.getMessage());
        }
    }
}
