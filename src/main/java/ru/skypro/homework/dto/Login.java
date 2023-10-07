package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Login {
    private String password;
    private String userName;



    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
