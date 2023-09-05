package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль не может быть меньше 8 и больше 16 символов")
    private String currentPassword;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Имя пользователя не может быть меньше 4 и больше 32 символов")
    private String newPassword;

}
