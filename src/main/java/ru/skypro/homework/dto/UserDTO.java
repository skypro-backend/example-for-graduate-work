package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constans.Role;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private Role role;
    private String photo;
}
