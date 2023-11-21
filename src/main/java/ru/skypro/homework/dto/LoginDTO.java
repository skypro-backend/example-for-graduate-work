package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Авторизация пользователя")
public class LoginDTO {
    @Schema(description = "логин")
    @NotBlank
    @Size(min = 10, max = 20)
    private String username;
    @Schema(description = "пароль")
    @NotBlank
    @Size(min = 10, max = 20)
    private String password;
}
