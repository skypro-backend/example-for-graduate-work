package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class ImageService {

    private final Path uploadRoot;

    public ImageService(@Value("${path.upload:uploads}") String uploadPath) {
        this.uploadRoot = Paths.get(uploadPath).toAbsolutePath().normalize();
    }

    public String saveAdImage(Long adId, MultipartFile image) {
        return saveImage("ads", adId, image, "/ads/" + adId + "/image");
    }

    public String saveUserImage(Long userId, MultipartFile image) {
        return saveImage("users", userId, image, "/users/" + userId + "/image");
    }

    public byte[] readAdImage(Long adId) {
        return readImage("ads", adId);
    }

    private String saveImage(String folder, Long entityId, MultipartFile image, String publicPath) {
        try {
            Path directory = uploadRoot.resolve(folder);
            Files.createDirectories(directory);
            deleteExistingFiles(directory, entityId);

            String extension = resolveExtension(image.getOriginalFilename());
            Path target = directory.resolve(entityId + extension);
            image.transferTo(target.toFile());
            return publicPath;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to save image", e);
        }
    }

    private byte[] readImage(String folder, Long entityId) {
        Path directory = uploadRoot.resolve(folder);
        try (Stream<Path> files = Files.list(directory)) {
            Path imagePath = files
                    .filter(path -> path.getFileName().toString().startsWith(entityId.toString()))
                    .findFirst()
                    .orElseThrow(NotFoundException::new);
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    private void deleteExistingFiles(Path directory, Long entityId) throws IOException {
        if (!Files.exists(directory)) {
            return;
        }
        try (Stream<Path> files = Files.list(directory)) {
            files.filter(path -> path.getFileName().toString().startsWith(entityId.toString()))
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            throw new IllegalStateException("Failed to delete old image", e);
                        }
                    });
        }
    }

    private String resolveExtension(String originalFilename) {
        if (originalFilename == null || !originalFilename.contains(".")) {
            return ".jpg";
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.'));
    }
}
