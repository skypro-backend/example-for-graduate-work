package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController

public class ImageController {
       @GetMapping(value = "/src/main/resources/users/{directory}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getUserImage(@PathVariable String directory) {
        try {
            Path fileContent = Path.of("src/main/resources/users/" + directory + "/user_image.jpg");
            return Files.readAllBytes(fileContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/src/main/resources/users/{directory}/ads/{filename}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getAdImage(@PathVariable String directory, @PathVariable String filename) {
        try {
            Path fileContent = Path.of("src/main/resources/users/" + directory + "/ads/"  + filename);
            return Files.readAllBytes(fileContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
