package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для смены пароля")
public class NewPasswordDto {

    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16, example = "oldPassword123")
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "новый пароль", minLength = 8, maxLength = 16, example = "newPassword456")
    @Size(min = 8, max = 16)
    private String newPassword;
}