package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    Environment env;

    @GetMapping(path = "{filename}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            MediaType.ALL_VALUE
    })
    public ResponseEntity<byte[]> getImage(
            @PathVariable(value = "filename", required = true) String filename) throws IOException {

        if (!filename.endsWith(".jpg") && !filename.endsWith(".png")) {
            return ResponseEntity.badRequest().build();
        }

        Path fullPath = Paths.get(env.getProperty("app.images.root-path"))
                .resolve(filename);

        if (!Files.exists(fullPath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] bytes = Files.readAllBytes(fullPath);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(detectMimeType(bytes)))
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .body(bytes);
    }

    private String detectMimeType(byte[] bytes) {
        if (bytes.length >= 4) {
            if (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8) return "image/jpeg";
            if (bytes[0] == (byte) 0x89 && bytes[1] == (byte) 0x50) return "image/png";
        }
        return "image/*";
    }
}