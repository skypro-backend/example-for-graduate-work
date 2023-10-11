package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PasswordUpdateRequestDTO {

    @Schema(hidden = true)
    private long userId;
    private String currentPassword;
    private String newPassword;


}
