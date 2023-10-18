package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String phone;
}
