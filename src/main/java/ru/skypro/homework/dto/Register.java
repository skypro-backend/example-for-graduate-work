package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Register {

    @Size(message = "введите от 4 до 32 символов", min = 4, max = 32)
    private String username;
    @Size(message = "введите от 8 до 16 символов", min = 8, max = 16)
    private String password;
    @Size(message = "введите от 2 до 16 символов", min = 2, max = 16)
    private String firstName;
    @Size(message = "введите от 2 до 16 символов", min = 2, max = 16)
    private String lastName;
    @Pattern(message = "введите номер телефона согласно шаблона +7(777)777-77-77", regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    private Role role;
}
