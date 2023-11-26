package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class ImageServiceImpl implements ImageService {

    @Value("${path.to.images.folder}")
    private String imagesDir;
    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void uploadImage(User user, MultipartFile avatarFile) throws IOException {
        logger.info("Загружаем аватар для студента с id - {}", studentId);
        Path path = saveToDisk(user, avatarFile);
        saveToDB(user, path, avatarFile);
    }

    @Override
    public void uploadImage(Ad ad, MultipartFile avatarFile) throws IOException {
        logger.info("Загружаем аватар для студента с id - {}", studentId);
        Path path = saveToDisk(ad, avatarFile);
        saveToDB(ad, path, avatarFile);
    }

    private Path saveToDisk(User user, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(
                imagesDir,
                user.getId() + "." + getExtensions(avatarFile.getOriginalFilename())
        );
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        return filePath;
    }

    private Path saveToDisk(Ad ad, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(
                imagesDir,
                ad.getId() + "." + getExtensions(avatarFile.getOriginalFilename())
        );
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        return filePath;
    }

    private void saveToDB (User user, Path filePath, MultipartFile imageFile) throws IOException{
        Image image = findImage(user.getId());
        image.setFilePath(filePath.toString());
        image.setFileSize(avatarFile.getSize());
        image.setMediaType(avatarFile.getContentType());
        image.setData(avatarFile.getBytes());
        imageRepository.save(image);
    }

    private void saveToDB (Ad ad, Path filePath, MultipartFile imageFile) throws IOException{
        Image image = findImage(ad.getId());
        image.setFilePath(filePath.toString());
        image.setFileSize(avatarFile.getSize());
        image.setMediaType(avatarFile.getContentType());
        image.setData(avatarFile.getBytes());
        imageRepository.save(image);
    }

    @Override
    public Image findImage(long id) {
        logger.info("Ищем изображение по его id - {}", id);
        return imageRepository
                .findById(id)
                .orElse(new Image());
    }
}
