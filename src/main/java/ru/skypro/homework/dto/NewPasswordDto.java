package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class NewPasswordDto {
    private String currentPassword; //minLength: 8, maxLength: 16
    private String newPassword; // //minLength: 8, maxLength: 16
}
