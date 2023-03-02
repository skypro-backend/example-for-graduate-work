package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private Role role;
}
