package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Добавление или обновление объявления")
public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 32)
    private String title;
    @Schema(description = "цена объявления")
    @NotNull
    @Positive
    private int price;
    @Schema(description = "текст объявления")
    @NotBlank
    @Size(min = 4, max = 100)
    private String description;

}
