package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Запрос на смену пароля")
public class NewPassword {
    @Schema(description = "Текущий пароль", example = "oldpass123", minLength = 8, maxLength = 16)
    @NotBlank(message = "Текущий пароль обязателен")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String currentPassword;

    @Schema(description = "Новый пароль", example = "newpass123", minLength = 8, maxLength = 16)
    @NotBlank(message = "Новый пароль обязателен")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String newPassword;
}