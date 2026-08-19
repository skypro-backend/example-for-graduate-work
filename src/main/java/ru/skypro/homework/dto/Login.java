package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "данные для входа в систему")
public class Login {

    @Schema(description = "логин (email)", example = "user@gmail.com")
    private String username;

    @Schema(description = "пароль", example = "password")
    private String password;
}