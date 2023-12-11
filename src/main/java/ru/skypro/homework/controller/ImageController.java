package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    /**
     * Getting image converted from byte array with use of media type from DB by id
     * <br>
     * Using {@link  ImageService#findImage(Integer)}
     * @param id
     * @return byte[]
     */
    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            "image/*"})
    public byte[] getImage(@PathVariable Integer id) {
        Image image = imageService.findImage(id);
        return image.getData();
    }
}
