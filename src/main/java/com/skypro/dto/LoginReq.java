package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO для запроса авторизации (логина).
 * <p>
 * Используется как тело запроса {@code POST /login}.
 * </p>
 */

@Data
public class LoginReq {

    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    private String password;
}
