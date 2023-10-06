package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/images")
@RequiredArgsConstructor
@Tag(name = "Изображения", description = "Добавление, получение, обновление и удаление изображений")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        byte[] image = imageService.getImageInByteById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
