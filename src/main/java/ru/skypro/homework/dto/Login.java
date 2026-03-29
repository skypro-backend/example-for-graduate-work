package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для авторизации")
public class Login {

    @Schema(description = "логин",
            minLength = 4,
            maxLength = 32,
            example = "john_doe")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "пароль",
            minLength = 8,
            maxLength = 16,
            example = "password123")
    @Size(min = 8, max = 16)
    private String password;

    public Login() {}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


