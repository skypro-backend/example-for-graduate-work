package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final Path imageDirectory;

    public ImageService(
            @Value("${app.images.directory}") String imageDirectory
    ) {
        this.imageDirectory = Paths.get(imageDirectory)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.imageDirectory);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not create image directory",
                    e
            );
        }
    }

    public String saveImage(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null
                && originalFilename.contains(".")) {
            extension = originalFilename.substring(
                    originalFilename.lastIndexOf(".")
            );
        }

        String fileName =
                UUID.randomUUID() + extension;

        Path filePath =
                imageDirectory.resolve(fileName)
                        .normalize();

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not save image",
                    e
            );
        }

        return fileName;
    }

    public byte[] getImage(String fileName) {

        Path filePath =
                imageDirectory.resolve(fileName)
                        .normalize();

        if (!filePath.startsWith(imageDirectory)) {
            throw new IllegalArgumentException(
                    "Invalid image path"
            );
        }

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Image not found"
            );
        }
    }

    public String getContentType(String fileName) {

        Path filePath =
                imageDirectory.resolve(fileName)
                        .normalize();

        try {
            String contentType =
                    Files.probeContentType(filePath);

            return contentType != null
                    ? contentType
                    : "application/octet-stream";

        } catch (IOException e) {
            return "application/octet-stream";
        }
    }
}