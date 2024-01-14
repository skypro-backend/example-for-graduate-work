package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {
    private Long author; //id автора объявления

    private String image;//ссылка на картинку объявления

    private int pk; //id объявления

    private int price;//цена объявления

    private String title;//заголовок объявления
}
