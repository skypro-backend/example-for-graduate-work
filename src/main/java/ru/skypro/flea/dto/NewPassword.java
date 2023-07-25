package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPassword {

    @Schema(description = "текущий пароль",
            minLength = 8,
            maxLength = 16)
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "новый пароль",
            minLength = 8,
            maxLength = 16)
    @Size(min = 8, max = 16)
    private String newPassword;

}
