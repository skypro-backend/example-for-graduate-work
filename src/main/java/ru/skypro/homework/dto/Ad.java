package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    /**
     * ID автора объявления
     */
    private int author;
    /**
     * Ссылка на картинку объявления
     */
    private String image;
    /**
     * ID объявления
     */
    private int pk;
    /**
     * Цена объявления
     */
    private int price;
    /**
     * Заголовок объявления
     */
    private String title;
}
