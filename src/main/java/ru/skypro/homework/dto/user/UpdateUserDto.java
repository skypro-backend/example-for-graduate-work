package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 10, message = "Имя не может быть меньше 3 и больше 10 символов")
    private String firstName;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 10, message = "Фамилия не может быть меньше 3 и больше 10 символов")
    private String lastName;
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Номер телефона должен быть в формате +7(999)111-11-11")
    private String phone;
}
