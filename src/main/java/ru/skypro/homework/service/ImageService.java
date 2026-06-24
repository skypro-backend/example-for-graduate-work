package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${app.images.dir}")
    private String imagesDir;

    public String saveImage(MultipartFile image) throws IOException {
        Path filePath = Paths.get(imagesDir);
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = filePath.resolve(fileName);

        Files.write(path, image.getBytes());
        return fileName;
    }

    public byte[] getImageBytes(String fileName) throws IOException {
        Path path = Paths.get(imagesDir).resolve(fileName);
        return Files.readAllBytes(path);
    }
}