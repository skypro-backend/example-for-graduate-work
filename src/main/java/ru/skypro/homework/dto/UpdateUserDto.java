package ru.skypro.homework.dto;

import lombok.Data;

/**
 * UpdateUserDto is the Data Transfer Object used to update information about an authorized user
 * @author radyushinaalena and AlexBoko
 */
@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String phone;

    public String getUsername() {
        return null;
    }
}