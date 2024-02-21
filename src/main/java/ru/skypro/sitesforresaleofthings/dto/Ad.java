package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO объявления
 */

/**
 * Свойства:
 * 1) author - id автора объявления,
 * 2) image - ссылка на картинку объявления,
 * 3) pk - id объявления,
 * 4) price - цена объявления,
 * 5) title - заголовок объявления
 */
@Data
public class Ad {

    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}