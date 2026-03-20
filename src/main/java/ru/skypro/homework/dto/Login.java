package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для авторизации")
public class Login {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "Логин", example = "user@mail.com")
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "Пароль", example = "password123")
    private String password;
}