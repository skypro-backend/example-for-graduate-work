package ru.skypro.homework.dto.auth;

import lombok.Data;
import ru.skypro.homework.entity.roles.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Имя пользователя не может быть меньше 4 и больше 32 символов")
    private String username;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 16, message = "Имя не может быть меньше 2 и больше 16 символов")
    private String firstName;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 16 , message = "Фамилия не может быть меньше 2 и больше 16 символов")
    private String lastName;

    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Номер телефона должен быть в формате +7(999)111-11-11")
    private String phone;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль не может быть меньше 8 и больше 16 символов")
    private String password;

    private Role role;

}
