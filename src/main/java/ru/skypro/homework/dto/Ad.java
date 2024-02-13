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
    private int author;
    /**
     * Ссылка на картинку объявления
     */
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    /**
     * ID объявления
     */
    @Schema(description = "id объявления")
    private int pk;
    /**
     * Цена объявления
     */
    @Schema(description = "цена объявления")
    private int price;
    /**
     * Заголовок объявления
     */
    @Schema(description = "заголовок объявления")
    private String title;
}
