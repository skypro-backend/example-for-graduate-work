package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * DTO для авторизации пользователя.
 * Используется в запросе POST /login.
 * Содержит логин и пароль для аутентификации.
 * Основные функции:
 * - передача учётных данных пользователя на сервер.
 */
@Data
@Schema(description = "Запрос на авторизацию")
public class Login {

    @Schema(description = "Логин пользователя", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль пользователя", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String password;
}