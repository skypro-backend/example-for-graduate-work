package ru.skypro.homework.data.dto.auth;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;
}
