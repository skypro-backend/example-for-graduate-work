package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "смена пароля")
public class NewPasswordDto {
    @Schema(description = "текущий пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;
    @Schema(description = "новый пароль")
    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;

}
