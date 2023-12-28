package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUser {
    private String firstName; //minLength: 3 maxLength: 10 имя пользователя

    private String lastName; //minLength: 3 maxLength: 10 фамилия пользователя

    private String phone; //pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2} телефон пользователя
}
