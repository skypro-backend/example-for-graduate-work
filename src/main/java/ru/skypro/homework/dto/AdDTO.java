package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {

    private Integer author;    // id автора объявления
    private String image;  // ссылка на картинку объявления
    private Integer pk;        // id объявления
    private String price;  // цена объявления
    private String title;  // заголовок объявления



}
