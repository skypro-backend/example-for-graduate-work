package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        byte[] image = imageService.getImage(fileName);

        MediaType mediaType = MediaType.IMAGE_JPEG;
        if(fileName.toLowerCase().endsWith(".png")){
            mediaType = MediaType.IMAGE_PNG;
        }

        return ResponseEntity.ok().contentType(mediaType).body(image);
    }
}
