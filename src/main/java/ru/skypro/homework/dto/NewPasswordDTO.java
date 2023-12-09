package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Обновление пароля авторизованного пользователя")
public class NewPasswordDTO {
    @Schema(description = "старый пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;
    @Schema(description = "новый пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;

}
