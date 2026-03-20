package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "Заголовок объявления", example = "Продам велосипед")
    private String title;

    @NotNull
    @Min(0)
    @Max(10000000)
    @Schema(description = "Цена объявления", example = "5000")
    private Integer price;

    @NotBlank
    @Size(min = 8, max = 64)
    @Schema(description = "Описание объявления", example = "Почти новый, в хорошем состоянии")
    private String description;
}