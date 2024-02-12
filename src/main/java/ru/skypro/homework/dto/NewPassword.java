package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * сущность NewPassword
 */
@Data
public class NewPassword {
    /**
     * текущий пароль
     */
    @Size(min = 8, max = 16)
    @Schema(description = "текущий пароль")
    String currentPassword;

    /**
     * новый пароль
     */
    @Size(min = 8, max = 16)
    @Schema(description = "новый пароль")
    String newPassword;
}
