package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO для регистрации нового пользователя.
 * Используется в запросе POST /register.
 * Содержит все необходимые данные для создания учётной записи.
 * Основные функции:
 * - передача данных нового пользователя на сервер;
 * - валидация всех полей (логин, пароль, имя, телефон, роль).
 */
@Data
@Schema(description = "Запрос на регистрацию")
public class Register {

    @Schema(description = "Логин пользователя", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль пользователя", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "Имя пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "Фамилия пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "Телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;
}
