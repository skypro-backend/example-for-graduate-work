package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class UserDto {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

}
