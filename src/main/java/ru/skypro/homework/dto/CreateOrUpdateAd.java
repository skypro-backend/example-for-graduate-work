package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "Цена объявления")
    @NotNull
    @Min(0)
    @Max(10000000)
    private Integer price;

    @Schema(description = "Описание объявления")
    @NotBlank
    @Size(min = 8, max = 64)
    private String description;
}