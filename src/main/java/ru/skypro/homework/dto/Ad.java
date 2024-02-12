package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * Сущность Ad
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
    private String image;
    /**
     * ID объявления
     */
    private int pk;
    /**
     * Цена объявления
     */
    private int price;
    /**
     * Заголовок объявления
     */
    private String title;
}
