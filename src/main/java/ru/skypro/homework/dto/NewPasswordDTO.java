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
    @Size(min = 10, max = 20)
    private String currentPassword;
    @Schema(description = "новый пароль")
    @NotBlank
    @Size(min = 10, max = 20)
    private String newPassword;

}
