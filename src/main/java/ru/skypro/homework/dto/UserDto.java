package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;

@Data

public class UserDto {
    @Getter
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

    public UserDto(){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.image = image;
    }
}
