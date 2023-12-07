package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Регистрация пользователя")
public class RegisterDTO {

    @Schema(description = "логин")
    @NotBlank
    @Size(min = 4, max = 32)
    private String username;
    @Schema(description = "пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
    @Schema(description = "имя")
    @NotBlank
    @Size(min = 2, max = 16)
    private String firstName;
    @Schema(description = "фамилия")
    @NotBlank
    @Size(min = 2, max = 16)
    private String lastName;
    @Schema(description = "номер телефона")
    @NotBlank
    @Pattern(regexp = "\\+7\s?\\(?\\d{3}\\)?\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Schema(description = "роль пользователя")
    @NotBlank
    private RoleDTO role;

}
