package ru.skypro.homework.dto;


import lombok.Data;

@Data
public class NewPassword {
    private String currentPassword; //minLength: 8 maxLength: 16 текущий пароль
    private String newPassword; //minLength: 8 maxLength: 16 новый пароль
}
