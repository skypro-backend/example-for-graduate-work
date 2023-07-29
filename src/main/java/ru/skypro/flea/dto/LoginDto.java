package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @Schema(description = "Login")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Password")
    @Size(min = 8, max = 16)
    private String password;

}
