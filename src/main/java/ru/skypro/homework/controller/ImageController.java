package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.model.Image;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageMapper imageMapper;

    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable Integer id) {
        ImageDTO imageDTO = new ImageDTO("/images/" + id);
        Image image = imageMapper.mapToEntity(imageDTO);
        return image.getData();
    }
}
