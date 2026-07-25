package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для обновления данных пользователя.
 * <p>
 * Используется как тело запроса {@code PATCH /users/me}.
 * Позволяет обновить имя, фамилию и телефон (логин и пароль не изменяются через этот DTO).
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;

    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
