package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    private Long author;
    private String image; //ссылка на картинку
    private Long pk; // id объявления
    private Integer price; //цена объявления
    private String title; //заголовок объявления
}
