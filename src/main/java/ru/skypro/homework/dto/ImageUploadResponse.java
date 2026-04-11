package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO-класс, представляющий ответ после загрузки изображения.
 * Включает ID изображения, URL, размер, тип файла и оригинальное имя.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ при загрузке изображения")
public class ImageUploadResponse {

    @Schema(description = "ID изображения (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "URL для доступа к изображению", example = "/images/550e8400-e29b-41d4-a716-446655440000.jpg")
    private String url;

    @Schema(description = "Размер файла в байтах", example = "102400")
    private Long size;

    @Schema(description = "Тип файла", example = "image/jpeg")
    private String contentType;

    @Schema(description = "Оригинальное имя файла", example = "my-photo.jpg")
    private String originalFilename;
}