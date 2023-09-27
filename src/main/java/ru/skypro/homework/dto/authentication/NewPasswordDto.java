package ru.skypro.homework.dto.authentication;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String currentPassword;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String newPassword;
}
