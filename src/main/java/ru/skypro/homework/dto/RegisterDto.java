package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

}
