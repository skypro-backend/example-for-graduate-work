package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewPasswordDto {
    private Integer id;
    private String currentPassword;
    private String newPassword;

}
