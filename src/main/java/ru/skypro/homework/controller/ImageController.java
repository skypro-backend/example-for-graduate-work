package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * REST-контроллер для отдачи (скачивания) сохраненных изображений.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/images/{name}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable String name) throws IOException {
        return imageService.getImageBytes(name);
    }
}