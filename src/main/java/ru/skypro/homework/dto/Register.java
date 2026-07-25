package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для регистрации пользователя")
public class Register {

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

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(
            description = "Имя пользователя",
            example = "Иван"
    )
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(
            description = "Фамилия пользователя",
            example = "Иванов"
    )
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(
            description = "Телефон пользователя",
            example = "+7 (999) 123-45-67"
    )
    private String phone;

    @Schema(
            description = "Роль пользователя",
            example = "USER"
    )
    private Role role;
}
