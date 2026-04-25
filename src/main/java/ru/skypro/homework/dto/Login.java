package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для авторизации пользователя")
public class Login {
    @Schema(description = "Логин (email)", example = "user@gmail.com", minLength = 4, maxLength = 32)
    @NotBlank(message = "Логин обязателен")
    @Size(min = 4, max = 32, message = "Логин должен быть от 4 до 32 символов")
    private String username;

    @Schema(description = "Пароль", example = "password123", minLength = 8, maxLength = 16)
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String password;
}