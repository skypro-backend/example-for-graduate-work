package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPassword {
    @NotNull
    @Size(min = 8, max = 16)
    @Schema(description = "Текущий пароль пользователя", example = "oldPass123")
    private String currentPassword;

    @NotNull
    @Size(min = 8, max = 16)
    @Schema(description = "Новый пароль пользователя", example = "newPass456")
    private String newPassword;
}
