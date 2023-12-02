package ru.skypro.homework.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String firsName;
    private String lastName;
    private String phone;
    private String role;
    private String image;

}