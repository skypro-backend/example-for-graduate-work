package ru.skypro.homework.dto.users;

import lombok.Data;
import ru.skypro.homework.dto.register.Role;

import javax.validation.constraints.Email;

@Data
public class UserDto {
    private Integer id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

}
