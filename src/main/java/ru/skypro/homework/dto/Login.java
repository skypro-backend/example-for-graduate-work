package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Login {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
