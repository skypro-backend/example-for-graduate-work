package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Авторизация пользователя")
public class Login {


    @Schema(description = "Имя пользователя")
    @NotBlank
    @Size(min = 8, max = 16)
    private String username;

    @Schema(description = "Пароль пользователя")
    @NotBlank
    @Size(min = 4, max = 32)
    private String password;
}
