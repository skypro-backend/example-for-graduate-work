package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}
