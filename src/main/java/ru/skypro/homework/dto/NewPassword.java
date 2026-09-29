package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Password change data")
public class NewPassword {

    @Schema(description = "Current password", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(description = "New password", minLength = 8, maxLength = 16)
    private String newPassword;
}