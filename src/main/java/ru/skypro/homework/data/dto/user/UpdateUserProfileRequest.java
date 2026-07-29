package ru.skypro.homework.data.dto.user;

import lombok.Data;

@Data
public class UpdateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String phone;
}
