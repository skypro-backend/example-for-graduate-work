package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Создание или обновление объявления")
public class CreateOrUpdateAd {
    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;

    @NotNull
    @Min(0)
    @Max(10000000)
    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    private Integer price;

    @NotBlank
    @Size(min = 8, max = 64)
    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;
}
