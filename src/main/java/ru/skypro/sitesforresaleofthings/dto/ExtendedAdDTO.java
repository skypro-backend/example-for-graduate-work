package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO расширенного объявления
 */

/**
 * Свойства:
 * 1) pk - id объявления,
 * 2) authorFirstName - имя автора объявления,
 * 3) authorLastName - фамилия автора объявления,
 * 4) description - описание объявления,
 * 5) email - электронная почта автора объявления,
 * 6) image - ссылка на картинку объявления,
 * 7) phone - телефон автора объявления,
 * 8) price - цена объявления,
 * 9) title - заголовок объявления
 */
@Data
public class ExtendedAdDTO {

    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}