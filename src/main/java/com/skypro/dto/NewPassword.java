package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для смены пароля.
 * <p>
 * Используется как тело запроса {@code POST /users/set_password}.
 * Содержит текущий и новый пароль для проверки и обновления.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {

    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;
}
