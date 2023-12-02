package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String fistName;
    private String lastName;
    private String phone;
    private String image;
}
