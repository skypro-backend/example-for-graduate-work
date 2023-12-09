package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Авторизация пользователя")
public class LoginDto {

    @Schema(description = "Имя пользователя")
    @NotBlank
    private String username;

    @Schema(description = "Пароль пользователя")
    @NotBlank
    private String password;


}
