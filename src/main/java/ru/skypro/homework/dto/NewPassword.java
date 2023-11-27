package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Смена пароля пользователя")
public class NewPassword {

    @Schema(description = "Текущий пароль пользователя")
    @NotBlank
    private String  currentPassword;
    @Schema(description = "Новый пароль пользователя")
    @NotBlank
    private String  newPassword;


}
