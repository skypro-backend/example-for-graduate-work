package ru.skypro.homework.dto.register;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {
    @Size(min = 8, max = 16)
    private String currentPassword;
    @Size(min = 8, max = 16)
    private String newPassword;

}
