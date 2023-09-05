package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {
    @Size(min = 2, max = 16, message = "Имя не может быть меньше 2 и больше 16 символов")
    private String firstName;
    @Size(min = 2, max = 16, message = "Фамилия не может быть меньше 2 и больше 16 символов")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Номер телефона должен быть в формате +7(999)111-11-11")
    private String phone;
}
