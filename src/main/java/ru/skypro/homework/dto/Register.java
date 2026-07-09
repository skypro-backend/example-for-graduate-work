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
@Schema(description = "Данные для регистрации пользователя")
public class Register {

    @Schema(description = "Email пользователя (логин)", example = "user@example.com")
    private String username;

    @Schema(description = "Пароль", example = "securePassword123")
    private String password;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+79161234567")
    private String phone;

    @Schema(description = "Роль пользователя", example = "USER")
    private Role role;
}