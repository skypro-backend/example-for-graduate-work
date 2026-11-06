package ru.skypro.homework.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "обновление пароля")
public record NewPasswordDto(
        @Schema(description = "текущий пароль")
        @NotBlank
        @Size(min = 8, max = 16)
        String currentPassword,

        @Schema(description = "новый пароль")
        @NotBlank
        @Size(min = 8, max = 16)
        String newPassword
) {
}
