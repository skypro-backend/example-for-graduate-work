package ru.skypro.homework.dto;

public record CreateOrUpdateAdDto(
        /**
         minLength: 4
         maxLength: 32
         заголовок объявления
         */
        String title,
        /**
         minimum: 0
         maximum: 10000000
         цена объявления
         */
        Integer price,
        /**
         minLength: 8
         maxLength: 64
         описание объявления
         */
        String description
) {
}
