package ru.skypro.homework.dto.users;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String firstname;
    private String lastName;
    private String phone;

}
