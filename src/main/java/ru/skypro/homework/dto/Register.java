package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Register {

    private String username; //main4; max32
    private String password;// min8; max 16
    private String firstName; // min2; max 16
    private String lastName;// min2; max 16
    private String phone; // pattern \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
    private Role role;
}
