package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements  ImageService
{@Value("${app.image.bucket}")
private String bucket;

    private final ImageRepository repository;

    @SneakyThrows
    private void upload(String imagePath, InputStream inputStream) {
        Path fullImagePath = Path.of(bucket, imagePath);
        try (inputStream) {
            Files.createDirectories(fullImagePath.getParent());
            Files.deleteIfExists(fullImagePath);
            Files.write(fullImagePath, inputStream.readAllBytes(), CREATE);
        }
    }

    @SneakyThrows
    @Override
    public void upload(String imagePath, MultipartFile file) {
        upload(imagePath, file.getInputStream());
    }

    @SneakyThrows
    @Override
    public void deleteFromDisk(String imagePath) {
        if (imagePath != null) {
            Files.deleteIfExists(Path.of(bucket, imagePath));
        }
    }

    @Transactional
    @Override
    public void delete(Image image) {
        repository.delete(image);
        deleteFromDisk(image.getImagePath());
    }

    @Transactional
    @Override
    public void update(Image image, MultipartFile file) {
        String oldImagePath = image.getImagePath();
        String newImagePath = createImagePath(file);
        upload(newImagePath, file);
        image.setImagePath(newImagePath);
        deleteFromDisk(oldImagePath);
        repository.save(image);
    }

    @SneakyThrows
    @Override
    public byte[] download(String imagePath) {
        Path fullImagePath = Path.of(bucket, imagePath);
        return Files.exists(fullImagePath) ? Files.readAllBytes(fullImagePath) : new byte[]{};
    }

    @Transactional
    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public String createImagePath(MultipartFile file) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
    }
}
