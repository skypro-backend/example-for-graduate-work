package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(
            description = "Заголовок объявления",
            example = "iPhone 15 Pro",
            minLength = 4,
            maxLength = 32
    )
    private String title;

    @NotNull
    @Min(0)
    @Max(10_000_000)
    @Schema(
            description = "Цена объявления",
            example = "100000",
            minimum = "0",
            maximum = "10000000"
    )
    private Integer price;

    @NotBlank
    @Size(min = 8, max = 64)
    @Schema(
            description = "Описание объявления",
            example = "Телефон в отличном состоянии",
            minLength = 8,
            maxLength = 64
    )
    private String description;
}