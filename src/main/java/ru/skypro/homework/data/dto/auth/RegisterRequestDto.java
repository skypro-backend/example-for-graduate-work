package ru.skypro.homework.data.dto.auth;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
