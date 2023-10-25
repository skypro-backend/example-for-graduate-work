package ru.skypro.homework.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record NewPasswordDto(
        @NotBlank(message = "Поле текущий пароль не может быть пустым")
        @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
        String currentPassword,
        @NotBlank(message = "Поле новый пароль не может быть пустым")
        @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
        String newPassword
) {
}
