package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String regDate;
    private String image;
}
