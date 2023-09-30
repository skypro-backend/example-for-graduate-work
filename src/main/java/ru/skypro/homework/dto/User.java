package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class User {
    String phone;
    String lastName;
    String firstName;
    String email;
    int id;
}
