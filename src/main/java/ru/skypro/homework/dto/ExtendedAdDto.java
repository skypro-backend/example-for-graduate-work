package ru.skypro.homework.dto;

public record ExtendedAdDto(
        /**
         id объявления
         */
        Integer pk,
        /**
         имя автора объявления
         */
        String authorFirstName,
        /**
         фамилия автора объявления
         */
        String authorLastName,
        /**
         описание объявления
         */
        String description,
        /**
         логин автора объявления
         */
        String email,
        /**
         ссылка на картинку объявления
         */
        String image,
        /**
         телефон автора объявления
         */
        String phone,
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
