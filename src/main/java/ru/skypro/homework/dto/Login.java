package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для авторизации пользователя")
public class Login {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(
            description = "Логин пользователя",
            example = "user@mail.ru"
    )
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(
            description = "Пароль пользователя",
            example = "password123"
    )
    private String password;
}