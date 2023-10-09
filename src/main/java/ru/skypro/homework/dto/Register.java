package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Register {

    private String userName;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;


    public String getUsername() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public Resource getRole() {
        return null;
    }
}
