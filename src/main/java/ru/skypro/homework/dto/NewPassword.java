package ru.skypro.homework.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;

}