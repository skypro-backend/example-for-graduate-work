package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Schema(description = "Запрос на авторизацию")
public class Login {

    @Schema(description = "Логин пользователя", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль пользователя", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String password;
}