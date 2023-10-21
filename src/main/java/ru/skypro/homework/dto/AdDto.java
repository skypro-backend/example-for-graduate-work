package ru.skypro.homework.dto;

public record AdDto(
        /**
         id автора объявления
         */
        Integer author,
        /**
         ссылка на картинку объявления
         */
        String image,
        /**
         id объявления
         */
        Integer pk,
        /**
         цена объявления
         */
        Integer price,
        /**
         заголовок объявления
         */
        String title
) {
}
