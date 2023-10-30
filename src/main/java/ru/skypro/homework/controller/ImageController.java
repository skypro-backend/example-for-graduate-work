package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.image.ImageService;
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    /**
     * Этот метод обрабатывает GET-запросы на получение картинки
     *
     * @param imageName название картинки
     * @return Возвращает байтовое представление картинки
     */
    @GetMapping(value = "/{imageName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage2(@PathVariable String imageName) {
        return imageService.getImage(imageName);
    }
}
