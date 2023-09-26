package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @Size(min = 4, max = 16)
    private String username;

    @Size(min = 8, max = 16)
    private String password;
}
