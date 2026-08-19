package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "данные для регистрации пользователя")
public class Register {

    @Schema(description = "логин (email)", example = "user@gmail.com")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "пароль", example = "password")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "имя пользователя", example = "Ivan")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Ivanov")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79991234567")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "роль пользователя", example = "USER")
    private Role role;
}
