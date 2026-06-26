package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Форма с полным набором регистрационных данных для создания нового аккаунта.
 * Содержит правила валидации имени, пароля и регулярное выражение для телефона.
 */
@Data
@Schema(description = "Форма регистрации пользователя")
public class Register {
    @Schema(description = "Логин (email)", example = "user@gmail.com")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль", example = "password123")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "Имя пользователя", example = "Иван")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+7(999)999-99-99")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;
}
