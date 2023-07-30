package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {
    private final AdsService adService;
    private final ImageService imageService;

    public ImageController(AdsService adService, ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }
    /**
     * Обновление изображения
     */
    @PatchMapping("ads/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image) throws UserNotFoundException
    {
        return ResponseEntity.ok().body(adService.updateAdImage(id, image));
    }

    /**
     * Отдает массив байтов по ссылке на картинку объявления
     */
    @GetMapping(value = "ads/image/{id}")
    public FileSystemResource getAdImage(
            @PathVariable Integer id) throws IOException
    {
        return imageService.getAdImage(id);
    }

    @GetMapping(value = "users/avatar/{id}")
    public FileSystemResource getUserImage(
            @PathVariable Integer id) throws IOException
    {
        return imageService.getUserImage(id);
    }
}
