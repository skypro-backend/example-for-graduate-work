package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User login data")
public class Login {

    @Schema(description = "Username", example = "user@test.com")
    private String username;

    @Schema(description = "Password", example = "password123")
    private String password;
}