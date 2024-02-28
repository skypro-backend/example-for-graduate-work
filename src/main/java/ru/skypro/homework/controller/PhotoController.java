package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.PhotoServiceImpl;

import java.io.IOException;


@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class PhotoController {
    private final PhotoServiceImpl photoService;


    @GetMapping("/image/{photoId}")
    public ResponseEntity<byte[]> getPhotoFromSource(@PathVariable Integer photoId) throws IOException {
        return ResponseEntity.ok(photoService.getPhoto(photoId));
    }
}
