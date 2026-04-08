package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Register {

    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    private String username;

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String password;

    @Size(min = 2, max = 16, message = "Имя пользователя должно содержать от 2 до 16 символов")
    private String firstName;

    @Size(min = 2, max = 16, message = "Фамилия пользователя должна содержать от 2 до 16 символов")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Телефон должен соответствовать формату +7 (XXX) XXX-XX-XX")
    private String phone;

    private Role role;
}
