package ru.skypro.homework.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPassword {

    @Size(message = "введите от 8 до 16 символов", min = 8, max = 16)
    private String currentPassword;
    @Size(message = "введите от 8 до 16 символов", min = 8, max = 16)
    private String newPassword;
}