package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для смены пароля")
public class NewPasswordDto {

    @Schema(description = "Текущий пароль", example = "oldPassword123")
    private String currentPassword;

    @Schema(description = "Новый пароль", example = "newSecurePassword456")
    private String newPassword;
}