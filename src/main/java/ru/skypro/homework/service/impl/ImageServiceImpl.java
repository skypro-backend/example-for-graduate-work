package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.AvatarEntity;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class ImageServiceImpl implements ImageService {

    private final AvatarRepository avatarRepository;
    private final PhotoRepository photoRepository;

    public ImageServiceImpl(AvatarRepository avatarRepository, PhotoRepository photoRepository) {
        this.avatarRepository = avatarRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public void updateUserImage(UserEntity user, MultipartFile image, Path filePath) {

        AvatarEntity avatar = avatarRepository.findByUser(user).orElseGet(AvatarEntity::new);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setUser(user);
        avatarRepository.save(avatar);
    }

    @Override
    public void updateAdImage(AdEntity ad, MultipartFile image, Path filePath) {

        PhotoEntity photo = photoRepository.findByAd(ad).orElseGet(PhotoEntity::new);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(image.getSize());
        photo.setMediaType(image.getContentType());
        photo.setAd(ad);
        photoRepository.save(photo);
    }

    @Override
    public void saveFileOnDisk(MultipartFile image, Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

    }

    @Override
    public String getExtension(String fileName) {

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
