package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}