package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для смены пароля")
public class NewPassword {

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "Текущий пароль", example = "oldPassword1")
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "Новый пароль", example = "newPassword1")
    private String newPassword;
}