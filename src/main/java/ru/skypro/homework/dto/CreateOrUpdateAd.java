package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {
    private Integer pk; // id объявления

    private String description; // описание объявления

    private String image; // ссылка на картинку объявления

    private Integer price; // цена объявления
    private String title; // заголовок объявления
}
