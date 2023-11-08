package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exceptions.FindNoEntityException;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.hibernate.cfg.BinderHelper.getPath;


@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;
    @Value("${path.to.images}")
    private String imageDirectory;

    @Override
    public Image saveImage(MultipartFile image) throws IOException {
        Image entity = repository.save(new Image());
        Path filePath = getPath(entity);
        Files.createDirectories(filePath.getParent());
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return entity;
    }

    @Override
    public byte[] getImage(long id) throws IOException {
        return Files.readAllBytes(getPath(getEntity(id)));
    }

    @Override
    public Image getEntity(long id) {
        return repository.findById(id).orElseThrow(() -> new FindNoEntityException("картинка"));
    }

    @Override
    public void deleteImage(Image image) throws IOException {
        Files.deleteIfExists(getPath(image));
        repository.delete(image);
    }

    private Path getPath(Image image) {
        return Path.of(imageDirectory, String.valueOf(image.getId()));
    }
}