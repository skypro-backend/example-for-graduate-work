package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * Ad
 */
@Data
public class Ad {
    /**
     * ID автора объявления
     */
    @Schema(description = "id автора объявления")
    Integer author;
//    int author;
    /**
     * Ссылка на картинку объявления
     */
    @Schema(description = "ссылка на картинку объявления")
    String image;
    /**
     * ID объявления
     */
    @Schema(description = "id объявления")
    Integer pk;
//    int pk;
    /**
     * Цена объявления
     */
    @Schema(description = "цена объявления")
    Integer price;
//    int price;
    /**
     * Заголовок объявления
     */
    @Schema(description = "заголовок объявления")
    String title;
}
