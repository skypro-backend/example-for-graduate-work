package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUser {
    @Schema(
            description = "Имя пользователя",
            example = "Иван",
            minLength = 2,
            maxLength = 50
    )
    private String firstName;
    @Schema(
            description = "Фамилия пользователя",
            example = "Петров",
            minLength = 2,
            maxLength = 50
    )
    private String lastName;
    @Schema(
            description = "Номер телефона в формате +7XXXXXXXXXX",
            example = "+79991234567",
            pattern = "^\\+7\\d{10}$"
    )
    private String phone;
}
