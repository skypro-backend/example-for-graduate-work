package ru.skypro.homework.dto.authentication;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Register {

    @Size(min = 4, max = 32, message = "Проверьте количество символов.")
    private String username;

    @Size(min = 8, max = 16, message = "Проверьте количество символов.")
    private String password;

    @Size(min = 2, max = 16, message = "Проверьте количество символов.")
    private String firstName;

    @Size(min = 2, max = 16, message = "Проверьте количество символов.")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Проверьте введённый номер телефона.")
    private String phone;
    private Role role;
}
