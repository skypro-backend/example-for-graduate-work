package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для входа в систему")
public class Login {

    @Schema(description = "Email пользователя", example = "user@example.com")
    private String username;

    @Schema(description = "Пароль", example = "securePassword123")
    private String password;
}