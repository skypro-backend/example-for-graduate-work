package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.kakavito.service.ImageService;

/**
 * Класс для управления потоком данных при работе с изображениями
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping(path = "${query.to.get.image}")
public class ImageController {
    private final ImageService imageService;

    /**
     * Запрос на получение массива байт изображения
     *
     * @param imageId
     * @return byte[]
     * @see ru.skypro.kakavito.model.Image
     */
    @GetMapping(value = "/{imageId}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            "image/*"
    })
    public ResponseEntity<byte[]> getImageById(@PathVariable int imageId) {
        return ResponseEntity.ok().body(imageService.findImageById(imageId).getData());
    }
}
