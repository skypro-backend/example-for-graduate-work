package ru.skypro.homework.models;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private Role role;
}
