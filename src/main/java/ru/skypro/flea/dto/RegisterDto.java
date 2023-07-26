package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.flea.model.enums.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @Schema(description = "Login")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Password")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "User's first name")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "User's last name")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "User's phone")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "User's role")
    private Role role;

}
