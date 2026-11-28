package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для авторизации")
public class Login {
    @Schema(description = "пароль", minLength = 8, maxLength = 16, example = "password123")
    private String password;

    @Schema(description = "логин", minLength = 4, maxLength = 32, example = "user@mail.ru")
    private String username;
}