package ru.skypro.kakavito.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String phone;
}
