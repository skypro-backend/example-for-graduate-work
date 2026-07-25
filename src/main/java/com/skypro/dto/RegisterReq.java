package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO для запроса регистрации нового пользователя.
 * <p>
 * Используется как тело запроса {@code POST /register}.
 * Содержит все необходимые поля для создания учётной записи.
 * </p>
 */

@Data
public class RegisterReq {

    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    private String password;

    @Schema(description = "имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;
}
