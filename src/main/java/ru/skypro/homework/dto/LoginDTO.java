package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Авторизация пользователя")
public class LoginDTO {
    @Schema(description = "логин")
    @NotBlank
    @Size(min = 4, max = 32)
    private String username;
    @Schema(description = "пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
