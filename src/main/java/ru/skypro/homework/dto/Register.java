package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.validation.Regex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Register {
    @Email(regexp = Regex.EMAIL_REGEXP)
    private String username;
    @NotBlank
    @Size(min = 8,max = 16)
    private String password;
    @NotBlank
    @Size(min = 2, max = 16)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp = Regex.PHONE_REGEXP)
    private String phone;
    private Role role;
}
