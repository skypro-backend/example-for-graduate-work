package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
public class UserModel {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

    public UserModel(int id, String email, String firstName, String lastName, String phone, Role role, String image){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }
}