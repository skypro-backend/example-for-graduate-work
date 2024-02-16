package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ExtendedAd
 */
@Data
public class ExtendedAd {
    /**
     * ID объявления
     */
    @Schema(description = "id объявления")
    int pk;
    /**
     * Имя автора объявления
     */
    @Schema(description = "имя автора объявления")
    String authorFirstName;
    /**
     * Фамилия автора объявления
     */
    @Schema(description = "фамилия автора объявления")
    String authorLastName;
    /**
     * Описание объявления
     */
    @Schema(description = "описание объявления")
    String description;
    /**
     * Логин автора объявления
     */
    @Schema(description = "логин автора объявления")
    String email;
    /**
     * Ссылка на картинку объявления
     */
    @Schema(description = "ссылка на картинку объявления")
    String image;
    /**
     * Телефон автора объявления
     */
    @Schema(description = "телефон автора объявления")
    String phone;

    /**
     * Цена объявления
     */
    @Schema(description = "цена объявления")
    int price;
    /**
     * Заголовок объявления
     */
    @Schema(description = "заголовок объявления")
    String title;
}
