package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${app.image.storage.path:uploads/images}")
    private String storagePath;


    public ImageEntity saveAdImage(MultipartFile file, AdEntity ad) {
        ImageEntity image = saveFile(file);
        image.setAd(ad);
        image = imageRepository.save(image);
        log.info("Сохранено изображение {} для объявления {}", image.getId(), ad.getId());
        return image;
    }


    public ImageEntity saveUserAvatar(MultipartFile file, UserEntity user) {
        // Удаляем старый аватар
        if (user.getImage() != null) {
            deleteImage(user.getImage());
        }

        ImageEntity image = saveFile(file);
        image.setUser(user);
        image = imageRepository.save(image);
        user.setImage(image);
        log.info("Обновлён аватар пользователя {}", user.getEmail());
        return image;
    }

    @Transactional(readOnly = true)
    public byte[] loadImage(String id) {
        ImageEntity image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Изображение не найдено: " + id));

        try {
            Path filePath = Paths.get(image.getFilePath());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.error("Ошибка чтения файла: {}", image.getFilePath(), e);
            throw new RuntimeException("Не удалось загрузить изображение", e);
        }
    }


    public void deleteImage(ImageEntity image) {
        try {
            Path filePath = Paths.get(image.getFilePath());
            Files.deleteIfExists(filePath);
            imageRepository.delete(image);
            log.info("Удалено изображение {}", image.getId());
        } catch (IOException e) {
            log.error("Ошибка удаления файла: {}", image.getFilePath(), e);
        }
    }


    private ImageEntity saveFile(MultipartFile file) {
        String id = UUID.randomUUID().toString();
        String extension = getFileExtension(file.getOriginalFilename());
        String fileName = id + extension;

        try {
            Path uploadPath = Paths.get(storagePath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            return ImageEntity.builder()
                    .id(id)
                    .filePath(filePath.toString())
                    .fileSize(file.getSize())
                    .mediaType(file.getContentType())
                    .build();

        } catch (IOException e) {
            log.error("Ошибка сохранения файла", e);
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }


    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}