package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {

    @Schema(description = "Current password")
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "New password")
    @Size(min = 8, max = 16)
    private String newPassword;

}
