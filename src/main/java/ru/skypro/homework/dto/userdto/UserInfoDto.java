package ru.skypro.homework.dto.userdto;

import lombok.Data;
import ru.skypro.homework.dto.authdto.Role;

@Data
public class UserInfoDto {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private String image;
}
