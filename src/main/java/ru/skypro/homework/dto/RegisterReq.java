package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterReq {
    @Schema(description = "логин")
    private String username;
    @Schema(description = "пароль")
    private String password;
    @Schema(description = "имя пользователя")
    private String firstName;
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Schema(description = "телефон пользователя")
    private String phone;
    @Schema(description = "роль пользователя", enumAsRef = true)
    private Role role;
}
