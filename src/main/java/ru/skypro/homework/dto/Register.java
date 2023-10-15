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
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
