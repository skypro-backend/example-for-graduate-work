package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Данные для регистрации пользователя")
public class Register {
    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    private String password;

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(description = "имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(description = "фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;
}
