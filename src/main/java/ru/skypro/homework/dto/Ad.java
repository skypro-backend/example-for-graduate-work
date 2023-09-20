package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Ad {
    private Integer author; //($int32) id автора объявления
    private String image; // ссылка на картинку объявления
    private Integer pk; //($int32)id объявления
    private Integer price; //($int32) цена объявления
    private String title; //заголовок объявления
}
