package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.*;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Service for receiving, deleting and uploading an image
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Value("${uploading.image.path}")
    private final String imagePath;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(@Value("${uploading.image.path}") String imagePath, ImageRepository imageRepository) {
        this.imagePath = imagePath;
        this.imageRepository = imageRepository;
    }

    /**
     * Gets binary code of an image
     * @param id of an image
     * @return binary code of an image
     */
    @Transactional
    @Override
    public byte[] getImage(Integer id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow(() -> new ImageIsNotFoundException("Image is not found"));
        try {
            Path imagePath = Path.of(imageEntity.getFilePath());
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new GetImageException("Failed to get a file.");
        }
    }

    /**
     * Deletes an image from file path and database
     * @param adEntity contains ad id, price, title, description, UserEntity, ImageEntity and a list of CommentEntities
     */
    public void deleteImage(AdEntity adEntity) {
        ImageEntity imageEntity = adEntity.getImageEntity();
        try {
            if (imageEntity != null) {
                Path iPath = Path.of(imageEntity.getFilePath());
                imageRepository.deleteById(imageEntity.getId());
                Files.delete(iPath);
            }
        } catch (IOException e) {
            throw new DeleteImageException("Failed to delete file.");
        }
    }

    /**
     * Deletes an image from file path and database
     * @param userEntity contains user id, email, firstname, lastname, phone, password, role, ImageEntity, a list of AdEntities and a list of CommentEntities
     */
    public void deleteImage(UserEntity userEntity) {
        ImageEntity imageEntity = userEntity.getImageEntity();
        try {
            if (imageEntity != null) {
                Path iPath = Path.of(imageEntity.getFilePath());
                imageRepository.deleteById(imageEntity.getId());
                Files.delete(iPath);
            }
        } catch (IOException e) {
            throw new DeleteImageException("Failed to delete file.");
        }
    }

    /**
     * Deletes an image from file path
     * @param adEntity contains ad id, price, title, description, UserEntity, ImageEntity and a list of CommentEntities
     */
    public void deleteImageFromPath(AdEntity adEntity) {
        ImageEntity imageEntity = adEntity.getImageEntity();
        try {
            if (imageEntity != null) {
                Path iPath = Path.of(imageEntity.getFilePath());
                Files.delete(iPath);
            }
        } catch (IOException e) {
            throw new DeleteImageException("Failed to delete file.");
        }
    }

    /**
     * Uploads an image to a database
     * @param file is an image
     * @return ImageEntity contains image id and file path
     */
    @Override
    public ImageEntity uploadImage(MultipartFile file) {
        Path filePath;
        if (file.getOriginalFilename() != null) {
            filePath = Path.of(imagePath + UUID.randomUUID() + "." + file.getOriginalFilename().split("\\.")[1]);
        } else throw new FilePathIsNullException("File Path is null");
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
