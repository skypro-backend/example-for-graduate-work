package ru.skypro.homework.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    @Min(0)
    @Max(10_000_000)
    private Integer price;

    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    @Size(min = 8, max = 64)
    private String description;
}
