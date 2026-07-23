package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Изображения", description = "API для получения изображений")
public class ImageController {

    private final ImageService imageService;


    @GetMapping(value = "/images/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            "image/*"
    })
    @Operation(summary = "Получить изображение по ID")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        log.info("GET /images/{}", id);
        byte[] imageBytes = imageService.loadImage(id);
        return ResponseEntity.ok(imageBytes);
    }
}