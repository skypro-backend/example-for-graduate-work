package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private Integer id;
    private String firstName = "имя пользователя";
    private String lastName = "фамилия пользователя";
    private String phone = "телефон пользователя";
}
