package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateAds {
    @Schema(description = "описание объявления", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(description = "цена объявления", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer price;
    @Schema(description = "заголовок объявления", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
}
