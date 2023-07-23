package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserUpdateReq {
    String firstName;
    String lastName;
    String phone;
}
