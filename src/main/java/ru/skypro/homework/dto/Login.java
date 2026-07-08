package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;

@Schema(description = "Логин пользователя")
public class Login {

    @Schema(description = "логин", example = "user@example.com")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "пароль", example = "1q2w3e")
    @Size(min = 8, max = 16)
    private String password;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}