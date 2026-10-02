package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.service.ImageStorageService;
import ru.skypro.homework.service.StoredImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileSystemImageStorageService implements ImageStorageService {

    private static final String DEFAULT_MEDIA_TYPE = "application/octet-stream";

    @Value("${app.image-storage.path}")
    private String storagePath;

    @Override
    public String save(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            Path storageDirectory = Path.of(storagePath);
            Files.createDirectories(storageDirectory);

            String fileName = UUID.randomUUID() + getExtension(image.getOriginalFilename());
            Path targetPath = storageDirectory.resolve(fileName);
            image.transferTo(targetPath);
            return fileName;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save image", e);
        }
    }

    @Override
    public StoredImage read(String fileName) {
        try {
            Path imagePath = Path.of(storagePath).resolve(fileName);
            if (!Files.exists(imagePath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            String mediaType = Files.probeContentType(imagePath);
            return new StoredImage(Files.readAllBytes(imagePath), mediaType == null ? DEFAULT_MEDIA_TYPE : mediaType);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not read image", e);
        }
    }

    private String getExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int extensionStart = fileName.lastIndexOf('.');
        return extensionStart < 0 ? "" : fileName.substring(extensionStart);
    }
}
