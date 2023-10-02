package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;

@Data
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
