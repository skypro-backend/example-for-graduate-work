package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @Size(min = 4, max = 32)
    String username;
    @Size(min = 8, max = 16)
    String password;

}
