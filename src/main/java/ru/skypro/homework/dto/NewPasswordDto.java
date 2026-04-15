package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String currentPassword;

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String newPassword;
}
