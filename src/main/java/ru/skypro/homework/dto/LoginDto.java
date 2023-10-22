package ru.skypro.homework.dto;

public record LoginDto(
        /**
         minLength: 4, maxLength: 16
         логин
         */
        String username,
        /**
         minLength: 4, maxLength: 16
         логин
         */
        String password
) {

}
