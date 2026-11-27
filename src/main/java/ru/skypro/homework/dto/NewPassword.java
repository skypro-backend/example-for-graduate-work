package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Смена пароля пользователя")
public class NewPassword {
    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;
}
