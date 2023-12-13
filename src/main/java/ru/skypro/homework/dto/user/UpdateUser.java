package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class UpdateUser {

    @Size(min = 3, max = 10, message = "Проверьте количество символов.")
    private String firstName;

    @Size(min = 3, max = 10, message = "Проверьте количество символов.")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Проверьте введённый номер телефона.")
    private String phone;



}
