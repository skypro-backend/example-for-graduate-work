package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель объявления")
public class Ad {
    @Schema(description = "ID автора объявления", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на картинку объявления", example = "/image/ad/1")
    private String image;

    @Schema(description = "ID объявления", example = "1")
    private Integer pk;

    @Schema(description = "Цена объявления", example = "10000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Велосипед")
    private String title;
}