package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewPassword {
    @Schema(description = "текущий пароль")
    private String currentPassword;
    @Schema(description = "новый пароль")
    private String newPassword;
}
