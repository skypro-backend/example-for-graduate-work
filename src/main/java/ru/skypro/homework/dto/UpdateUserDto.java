package ru.skypro.homework.dto;

public record UpdateUserDto(
        /**
         minLength: 3
         maxLength: 10
         имя пользователя
         */
        String firstName,
        /**
         minLength: 3
         maxLength: 10
         фамилия пользователя
         */
        String lastName,
        /**
         pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
         телефон пользователя
         */
        String phone
) {
}
