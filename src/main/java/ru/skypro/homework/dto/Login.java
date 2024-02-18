package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Login  пользователя
 * <br><i>содержит следующие поля:</i>
 * <br>- username <i>(логин)</i>;
 * <br>- password <i>(пароль)</i>;
 */
@Data
public class Login {
    /**
     * логин
     */
    @Size(min = 4, max = 32)
    @Schema(description = "логин")
    @Email
    private String username;

    /**
     * пароль
     */
    @Size(min = 8, max = 16)
    @Schema(description = "пароль")
    private String password;
}
