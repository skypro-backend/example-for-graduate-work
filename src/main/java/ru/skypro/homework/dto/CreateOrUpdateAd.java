package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {
    private String title; //minLength: 4 maxLength: 32 заголовок объявления

    private int price; //minimum: 0 maximum: 10000000 цена объявления

    private String description; //minLength: 8 maxLength: 64 описание объявления
}
