package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для регистрации")
public class Register {
    @Schema(description = "логин", minLength = 4, maxLength = 32, example = "user@mail.ru")
    private String username;

    @Schema(description = "пароль", minLength = 8, maxLength = 16, example = "password123")
    private String password;

    @Schema(description = "имя пользователя", minLength = 2, maxLength = 16, example = "Иван")
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 2, maxLength = 16, example = "Иванов")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+7 (999) 123-45-67")
    private String phone;

    @Schema(description = "роль пользователя", example = "USER")
    private Role role; // На этапе 1 оставим String, на 2-м поменяем на Enum Role
}