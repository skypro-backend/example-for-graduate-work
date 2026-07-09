package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "обновление пароля")
public class NewPassword {
    @Schema(description = "текущий пароль", example = "12345QWE")
    private String currentPassword;
    @Schema(description = "новый пароль", example = "12345QWE")
    private String newPassword;
}
