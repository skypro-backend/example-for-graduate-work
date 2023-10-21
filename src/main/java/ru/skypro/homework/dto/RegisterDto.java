package ru.skypro.homework.dto;

import ru.skypro.homework.enums.Role;

public record RegisterDto(
        /**
         minLength: 4,maxLength: 32
         логин
         */
        String username,
        /**
         minLength: 8,maxLength: 16
         пароль
         */
        String password,
        /**
         minLength: 2,maxLength: 16
         имя пользователя
         */
        String firstName,
        /**
         minLength: 2, maxLength: 16
         фамилия пользователя
         */
        String lastName,
        /**
         pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
         телефон пользователя
         */
        String phone,
        /**
         роль пользователя
         Enum: Array [ 2 ]
         */
        Role role) {

    }


