package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Смена пароля")
public class NewPassword {

    @Schema(description = "Текущий пароль", example = "oldPassword123")
    private String currentPassword;

    @Schema(description = "Новый пароль", example = "newPassword123")
    private String newPassword;
}