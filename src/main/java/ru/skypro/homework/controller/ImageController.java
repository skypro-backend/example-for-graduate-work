package ru.skypro.homework.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{imageName}")
    public byte[] getImage(@PathVariable String imageName) {
        return imageService.getImageInByte(imageName);
    }

}
