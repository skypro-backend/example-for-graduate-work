package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Авторизация пользователя")
public class LoginDTO {
    @Schema(description = "логин")
    @NotBlank
    @Size(min = 10, max = 20)
    private String username;
    @Schema(description = "пароль")
    @NotBlank
    @Size(min = 10, max = 20)
    private String password;
}
