package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.constants.ErrorMessages;
import ru.skypro.homework.service.image.ImageStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {


    private final ImageStorageService imageStorageService;


    @GetMapping("/{folder}/{fileName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable String folder,
            @PathVariable String fileName
    ) {

        try {

            String path =
                    "images/"
                            + folder
                            + "/"
                            + fileName;

            byte[] image =
                    imageStorageService.loadImage(path);


            String contentType =
                    Files.probeContentType(
                            Paths.get(path)
                    );


            return ResponseEntity
                    .ok()
                    .contentType(
                            MediaType.parseMediaType(
                                    contentType != null
                                            ? contentType
                                            : "image/*"
                            )
                    )
                    .body(image);


        } catch (IOException e) {

            throw new RuntimeException(
                    ErrorMessages.IMAGE_LOAD_ERROR
            );
        }
    }
}