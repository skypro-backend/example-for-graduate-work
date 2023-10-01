package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;
}
