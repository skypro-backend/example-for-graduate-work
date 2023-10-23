package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/users/me/")
public class AvatarController {

    private final ImageService imageService;

    public AvatarController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value ="/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            imageService.uploadImage(file);
            return ResponseEntity.ok("Avatar uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }
}
