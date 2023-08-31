package ru.skypro.homework.dto.auth;

import lombok.Data;
import ru.skypro.homework.entity.users.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @Size(min = 4, max = 32)
    private String username;
    @Size(min = 2, max = 16)
    private String firstName;
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Неверный формат введенного номера телефона")
    private String phone;
    @Size(min = 8, max = 16)
    private String password;
    private Role role;

}
