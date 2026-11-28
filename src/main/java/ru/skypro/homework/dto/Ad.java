package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Краткая информация об объявлении")
public class Ad {
    @Schema(description = "id автора объявления", example = "1")
    private Integer author;

    @Schema(description = "ссылка на картинку объявления", example = "/images/1.jpg")
    private String image;

    @Schema(description = "id объявления", example = "1")
    private Integer pk;

    @Schema(description = "цена объявления", example = "1000")
    private Integer price;

    @Schema(description = "заголовок объявления", example = "Продам телефон")
    private String title;
}