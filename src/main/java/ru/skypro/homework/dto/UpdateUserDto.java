package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String name;
    private String surname;
    private String phoneNumber;
}
