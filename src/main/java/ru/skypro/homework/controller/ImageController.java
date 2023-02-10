package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    @PostMapping("/addImage")
    public ResponseEntity<String> saveImage(@RequestParam MultipartFile image, @PathVariable AdsDto adsDto){
        return ResponseEntity.ok(imageService.saveImage(image,adsDto));
    }
    @GetMapping(value = "/image/{id}/", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte [] getImage(@PathVariable Integer id){
        return imageService.getImage(id);
    }
}
