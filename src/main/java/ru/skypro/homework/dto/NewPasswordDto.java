package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Смена пароля пользователя")
public class NewPasswordDto {

    @Schema(description = "Текущий пароль пользователя")
    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "Новый пароль пользователя")
    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;
}
