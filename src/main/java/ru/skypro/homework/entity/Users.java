package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//этот класс станет entity
public class Users {
    private Integer id;
    private String email;
    private String image;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}