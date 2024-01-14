package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping
public class ImageController {

    private final ImageServiceImpl imageService;

    /**
     * Отображение картинок на странице
     */
    @GetMapping("image/{id}")
    private ResponseEntity<?> getImageById(@PathVariable int id) {

        return imageService.getImage(id);
    }

//    /**
//     * Обновление картинки объявления
//     */
//    @PatchMapping("/ads/{id}/image")
//    public ResponseEntity<String> updateImage(@PathVariable int id,
//                                              @RequestPart("image") MultipartFile image,
//                                              Authentication authentication) {
//        return ResponseEntity.ok(imageService.updateImage(id, image, authentication));
//    }
//
//    /**
//     * Обновление аватарки пользователя
//     */
//    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image,
//                                             Authentication authentication) {
//
//        return ResponseEntity.ok(imageService.updateImage(image, authentication));
//    }

}
