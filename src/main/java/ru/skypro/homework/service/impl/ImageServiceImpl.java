package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.StorageException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${uploading.image.path}")
    private final String imagePath;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(@Value("${uploading.image.path}") String imagePath, ImageRepository imageRepository) {
        this.imagePath = imagePath;
        this.imageRepository = imageRepository;
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow();
        Path imagePath = Path.of(imageEntity.getFilePath());
        return Files.readAllBytes(imagePath);
    }

    @Override
    public ImageEntity uploadImage(MultipartFile file) {
// TODO: unique file name, save in DB only filename, resize image?, why copy
        Path filePath = Path.of(imagePath + file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.");
        }

        return ImageEntity.builder().
                filePath(filePath.toString()).build();
    }
}