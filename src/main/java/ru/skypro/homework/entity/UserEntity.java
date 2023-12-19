package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;
@Data
public class UserEntity {
    private Integer pk;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String gender;
    private String phoneNumber;
    private Role userRole;
    private String idImage;
    private int userBirthday;


}
