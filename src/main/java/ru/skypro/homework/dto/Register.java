package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User registration data")
public class Register {

    @Schema(description = "Username", example = "user@test.com")
    private String username;

    @Schema(
            description = "Password",
            minLength = 8,
            maxLength = 16,
            example = "password123"
    )
    private String password;

    @Schema(description = "First name", example = "Dmitry")
    private String firstName;

    @Schema(description = "Last name", example = "Petrov")
    private String lastName;

    @Schema(description = "Phone number", example = "+37120000000")
    private String phone;

    @Schema(description = "User role")
    private Role role;
}