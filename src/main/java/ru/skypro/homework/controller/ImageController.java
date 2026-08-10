package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{subDirectory}/{fileName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_GIF_VALUE, "image/*"})
    public ResponseEntity<byte[]> getImage(
            @PathVariable String subDirectory,
            @PathVariable String fileName) {

        log.debug("Getting image: subDirectory={}, fileName={}", subDirectory, fileName);

        String filePath = subDirectory + "/" + fileName;
        byte[] image = imageService.getImage(filePath);

        if (image == null) {
            log.warn("Image not found: {}", filePath);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Определяем Content-Type по расширению
        String contentType = getContentType(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }

    private String getContentType(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "image/jpeg";
        }

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        switch (extension) {
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            case "svg":
                return "image/svg+xml";
            default:
                return "image/jpeg";
        }
    }
}
