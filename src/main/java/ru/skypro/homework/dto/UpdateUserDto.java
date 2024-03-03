package ru.skypro.homework.dto;

import java.util.Objects;
import lombok.Data;

@Data
public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String phone;


}
