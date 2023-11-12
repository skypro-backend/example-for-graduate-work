package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class NewPassword {
     @Size(min = 8, max = 16, message = "Текущий пароль слишком длинный или короткий.")
     private String currentPassword;

     @Size(min = 8, max = 16, message = "Новый пароль слишком длинный или короткий.")
     private String newPassword;
}
