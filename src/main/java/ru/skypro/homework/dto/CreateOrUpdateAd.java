package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO, используемое клиентом для передачи параметров создания или обновления объявления.
 */
@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;
    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    private Integer price;
    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;
}