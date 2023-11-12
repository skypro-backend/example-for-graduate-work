package ru.skypro.homework.dto.authentication;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Login {

    @Size(min = 8, max = 16, message = "Проверьте количество символов.")
    private String username;

    @Size(min = 4, max = 32, message = "Проверьте количество символов.")
    private String password;
}
