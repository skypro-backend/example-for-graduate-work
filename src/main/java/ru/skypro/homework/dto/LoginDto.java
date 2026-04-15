package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    private String username;

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String password;
}
