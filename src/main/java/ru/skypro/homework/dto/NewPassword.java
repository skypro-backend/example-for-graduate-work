package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "данные для смены пароля")
public class NewPassword {

    @Schema(description = "текущий пароль", example = "old_password_123")
    private String currentPassword;

    @Schema(description = "новый пароль", example = "new_password_456")
    private String newPassword;
}