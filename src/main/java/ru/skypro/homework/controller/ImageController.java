package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    @PatchMapping("/image/{id}")
    public ResponseEntity<String> updateAdsImage(@PathVariable Integer id,@RequestParam MultipartFile image){
        return ResponseEntity.ok(imageService.updateAdsImage(id, image));
    }
}
