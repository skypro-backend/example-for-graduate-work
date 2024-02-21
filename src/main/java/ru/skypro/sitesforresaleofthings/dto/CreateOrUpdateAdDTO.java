package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO создания или обновления объявления
 */

/**
 * Свойства:
 * 1) title - заголовок объявления,
 * 2) price - цена объявления,
 * 3) description - описание объявления
 */
@Data
public class CreateOrUpdateAdDTO {

    private String title;
    private Integer price;
    private String description;
}