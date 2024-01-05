package ru.skypro.kakavito.dto;

import lombok.Data;

/**
 * Создание ДТО
 */
@Data
public class CreateOrUpdateAdDTO {
    private String title;
    private Integer price;
    private String description;
}
