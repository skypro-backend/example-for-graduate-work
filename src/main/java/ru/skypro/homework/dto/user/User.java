package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class User {
    private String phone;
    private String lastName;
    private String firstName;
    private String email;
    private int id;
}
