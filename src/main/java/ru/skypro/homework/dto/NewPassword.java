package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * DTO для смены пароля.
 * Используется в запросе POST /users/set_password.
 * Основные функции:
 * - передача текущего и нового пароля для обновления учётных данных пользователя.
 */
@Data
@Schema(description = "Объект для смены пароля")
public class NewPassword {

    @Schema(description = "Текущий пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "Новый пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String newPassword;

}
