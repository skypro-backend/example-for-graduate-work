package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginReq {
    @Schema(description = "пароль")
    private String password;
    @Schema(description = "логин")
    private String username;

}
