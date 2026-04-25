package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "DTO для создания или обновления объявления")
public class CreateOrUpdateAd {
    @Schema(description = "Заголовок объявления", example = "Велосипед", minLength = 4, maxLength = 32)
    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 4, max = 32, message = "Заголовок должен быть от 4 до 32 символов")
    private String title;

    @Schema(description = "Цена объявления", example = "10000", minimum = "0", maximum = "10000000")
    private Integer price;

    @Schema(description = "Описание объявления", example = "Как новый", minLength = 8, maxLength = 64)
    @Size(min = 8, max = 64, message = "Описание должно быть от 8 до 64 символов")
    private String description;
}