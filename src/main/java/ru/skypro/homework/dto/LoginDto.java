package ru.skypro.homework.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "Поле логин не может быть пустым")
        @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
        @Email
        String username,
        @NotBlank(message = "Поле пароль не может быть пустым")
        @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
        String password
) {

}
