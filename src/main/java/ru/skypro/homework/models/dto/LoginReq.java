package ru.skypro.homework.models.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String password;
    private String username;

}
