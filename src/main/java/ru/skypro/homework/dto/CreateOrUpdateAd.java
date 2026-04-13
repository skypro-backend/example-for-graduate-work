package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "цена объявления")
    @Min(0)
    @Max(10000000)
    private int price;

    @Schema(description = "описание объявления")
    @Size(min = 8, max = 64)
    private String description;
}