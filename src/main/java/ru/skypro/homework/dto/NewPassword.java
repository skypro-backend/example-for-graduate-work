package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class NewPassword {
    private String currentPassword; //minLength: 8, maxLength: 16
    private String newPassword; // //minLength: 8, maxLength: 16
}
