package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000") // Для работы с фронтом
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        byte[] data = imageService.getImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Можно настроить динамически, но JPEG/PNG обычно достаточно
                .body(data);
    }
}
