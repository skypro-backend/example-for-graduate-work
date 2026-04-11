package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ImageUploadResponse;
import ru.skypro.homework.service.ImageService;

/**
 * Контроллер для обработки HTTP-запросов, связанных с загрузкой и получением изображений.
 * Обеспечивает API-методы для загрузки изображений на сервер и получения изображений по их идентификаторам.
 */
@Slf4j
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Tag(name = "Изображения", description = "Загрузка и получение изображений")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка изображения", operationId = "uploadImage")
    @ApiResponse(responseCode = "201", description = "Изображение успешно загружено",
            content = @Content(schema = @Schema(implementation = ImageUploadResponse.class)))
    public ResponseEntity<ImageUploadResponse> uploadImage(
            @Parameter(description = "Файл изображения", required = true)
            @RequestParam("image") MultipartFile file) {

        log.info("Загрузка изображения: {}", file.getOriginalFilename());

        String imageUrl = imageService.saveImage(file);

        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        String uuid = filename.contains(".")
                ? filename.substring(0, filename.lastIndexOf("."))
                : filename;

        ImageUploadResponse response = ImageUploadResponse.builder()
                .id(uuid)
                .url(imageUrl)
                .size(file.getSize())
                .contentType(file.getContentType())
                .originalFilename(file.getOriginalFilename())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение изображения", operationId = "getImage")
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "ID изображения", required = true)
            @PathVariable String id) {

        log.info("Получение изображения: {}", id);

        String filename = id;

        try {
            byte[] imageBytes = imageService.getImage(filename);

            String extension = filename.contains(".")
                    ? filename.substring(filename.lastIndexOf(".")).toLowerCase()
                    : ".jpg";

            String contentType = switch (extension) {
                case ".png" -> MediaType.IMAGE_PNG_VALUE;
                case ".gif" -> MediaType.IMAGE_GIF_VALUE;
                case ".webp" -> "image/webp";
                case ".bmp" -> "image/bmp";
                default -> MediaType.IMAGE_JPEG_VALUE;
            };

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);

        } catch (Exception e) {
            log.warn("Изображение не найдено: {}", filename);
            return ResponseEntity.notFound().build();
        }
    }
}