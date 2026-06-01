package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Логин пользователя")
public class Login {

    @Schema(description = "Логин пользователя", example = "Ivan")
    private String username;

    @Schema(description = "Пароль пользователя", example = "1q2w3e")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
