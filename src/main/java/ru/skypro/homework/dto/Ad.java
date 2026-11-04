package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Объявление")
public class Ad {

    @Schema(description = "id автора объявления", example = "1")
    private int author;

    @Schema(description = "ссылка на картинку объявления", example = "/images/1")
    private String image;

    @Schema(description = "id объявления", example = "1")
    private int pk;

    @Schema(description = "цена объявления", example = "5000")
    private int price;

    @Schema(description = "заголовок объявления", example = "Кухня на заказ")
    private String title;
}