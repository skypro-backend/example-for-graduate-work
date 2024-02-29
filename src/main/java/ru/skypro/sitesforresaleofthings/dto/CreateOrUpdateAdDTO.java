package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(min = 4, max = 32)
    private String title;
    @Size(max = 10000000)
    private Integer price;
    @NotBlank
    @Size(min = 8, max = 64)
    private String description;
}