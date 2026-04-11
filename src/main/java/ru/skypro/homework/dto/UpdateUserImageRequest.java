package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO-класс для запроса на обновление аватара пользователя.
 * Включает изображение файла.
 */
@Data
@Schema(description = "Запрос на обновление аватара")
public class UpdateUserImageRequest {

    @Schema(description = "Файл изображения", type = "string", format = "binary", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile image;
}
