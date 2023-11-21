package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Добавление или обновление объявления")
public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 25)
    private String title;
    @Schema(description = "цена объявления")
    @NotNull
    @Positive
    private int price;
    @Schema(description = "текст объявления")
    @NotBlank
    @Size(min = 4, max = 25)
    private String description;

}
