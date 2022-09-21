package ru.skypro.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // todo возможно удалить
public class UserDto {
    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String phone;
}
