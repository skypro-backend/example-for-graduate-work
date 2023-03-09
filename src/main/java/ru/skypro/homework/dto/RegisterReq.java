package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String phone;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
