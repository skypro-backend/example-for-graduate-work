package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32, example = "Продам телефон")
    private String title;

    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000", example = "1000")
    private Integer price;

    @Schema(description = "описание объявления", minLength = 8, maxLength = 64, example = "Хороший телефон, не битый")
    private String description;
}