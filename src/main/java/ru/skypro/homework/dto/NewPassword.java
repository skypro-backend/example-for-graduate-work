package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для изменения пароля пользователя")
public class NewPassword {

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(
            description = "Текущий пароль пользователя",
            example = "oldPassword123"
    )
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(
            description = "Новый пароль пользователя",
            example = "newPassword123"
    )
    private String newPassword;
}
