package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.constants.ApiConstants;
import ru.skypro.homework.service.ImageStorageService;

@Tag(name = "Картинки")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageStorageService imageStorageService;

    @Operation(summary = "Получение картинки по имени файла",
            description = "Отдаёт байты картинки пользователя или объявления по её ссылке")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping(value = "/{fileName}", produces = {
            MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"
    })
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        String imageUrl = ApiConstants.IMAGES_URL_PREFIX + fileName;
        byte[] data = imageStorageService.read(imageUrl);
        String contentType = imageStorageService.detectContentType(imageUrl);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(data);
    }
}