package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.Size;

/**
 * Данные формы авторизации пользователя при входе в систему.
 */
@Data
@Schema(description = "Форма авторизации пользователя")
public class Login {
    @Schema(description = "Логин (email)", example = "user@gmail.com")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль", example = "password123")
    @Size(min = 8, max = 16)
    private String password;
}
