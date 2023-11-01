package ru.skypro.homework.dto;

import lombok.Data;

/**
 * NewPasswordDto is the Data Transfer Object used to update the user's password
 * @author radyushinaalena and AlexBoko
 */
@Data
public class NewPasswordDto {
    private String currentPassword;
    private String newPassword;
}
