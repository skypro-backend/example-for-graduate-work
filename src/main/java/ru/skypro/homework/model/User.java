package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.RoleDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDTO role;
    private String image;
    private String username;
    private String password;
}
