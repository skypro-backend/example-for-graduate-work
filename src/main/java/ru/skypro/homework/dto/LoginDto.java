package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "регистрация пользователя")
public class LoginDto {

    @Schema(description = "логин")
    @NotBlank
    @Size(min = 4, max = 32)
    private String username;
    @Schema(description = "пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
