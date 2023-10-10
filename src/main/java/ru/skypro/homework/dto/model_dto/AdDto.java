package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO для передачи информации об объявлении
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDto {
    private Integer pk; // id объявления
    private Integer author; // id автора объявления
    private String image; // ссылка на картинку объявления
    private Integer price; // цена объявления
    private String title; // заголовок объявления

}
