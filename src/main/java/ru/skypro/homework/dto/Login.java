package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @NotNull(message = "Логин обязателен")
    @Size(min = 4, max = 32, message = "Логин должен быть от 4 до 32 символов")
    @Schema(description = "Логин пользователя для входа", example = "user_ivan")
    private String username;

    @NotNull(message = "Пароль обязателен")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    @Schema(description = "Пароль пользователя", example = "strongPass12")
    private String password;
}
