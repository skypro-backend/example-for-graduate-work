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
    @Value("${uploading.image.ad.path}")
    private final String imageAdPath;

    @Value("${uploading.image.user.path}")
    private final String imageUserPath;

    private final ImageRepository imageRepository;

    public ImageServiceImpl( @Value("${uploading.image.ad.path}") String imageAdPath, @Value("${uploading.image.user.path}") String imageUserPath, ImageRepository imageRepository) {
        this.imageAdPath = imageAdPath;
        this.imageUserPath = imageUserPath;
        this.imageRepository = imageRepository;
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow();
        Path imagePath = Path.of(imageEntity.getFilePath());
        return Files.readAllBytes(imagePath);

    }

    @Override
    public ImageEntity uploadAdImage(MultipartFile file) throws IOException {

        Path filePath = Path.of(imageAdPath + file.getOriginalFilename());
        storeImage(filePath, file);

        return ImageEntity.builder().
                filePath(filePath.toString()).build();
    }

    @Override
    public ImageEntity uploadUserImage(MultipartFile file) throws IOException {

        Path filePath = Path.of(imageUserPath + file.getOriginalFilename());

        storeImage(filePath, file);

        return ImageEntity.builder().
                filePath(filePath.toString()).build();
    }

    private void storeImage(Path filePath, MultipartFile file){
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.");
        }
    }
}
