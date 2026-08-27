package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${images.dir.path}")
    private String imagesDir;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Path filePath = Paths.get(imagesDir);
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
        }

        String fileName = UUID.randomUUID().toString() + "." + getExtension(file.getOriginalFilename());
        Path targetPath = filePath.resolve(fileName);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(targetPath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return fileName;
    }

    @Override
    public byte[] getImage(String fileName) throws IOException {
        Path path = Paths.get(imagesDir).resolve(fileName);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Картинка не найдена");
        }
        return Files.readAllBytes(path);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}