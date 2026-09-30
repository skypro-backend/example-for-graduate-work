package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(
            ImageService imageService
    ) {
        this.imageService = imageService;
    }

    @Operation(summary = "Get image")
    @ApiResponse(responseCode = "200", description = "Image returned")
    @ApiResponse(responseCode = "404", description = "Image not found")
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable String fileName
    ) {

        byte[] image =
                imageService.getImage(fileName);

        String contentType =
                imageService.getContentType(fileName);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType(contentType)
                )
                .body(image);
    }
}