package ru.skypro.homework.dto;

import ru.skypro.homework.enums.Role;

public record UserDto(
        /**
         id пользователя
         */
        Integer id,
        /**
         логин пользователя
         */
        String email,
        /**
         имя пользователя
         */
        String firstName,
        /**
         фамилия пользователя
         */
        String lastName,
        /**
         телефон пользователя
         */
        String phone,
        /**
         роль пользователя
         */
        Role role,
        /**
         ссылка на аватар пользователя
         */
        String image
) {
}
