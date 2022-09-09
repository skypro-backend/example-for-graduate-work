package ru.skypro.homework.models.entity;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.models.dto.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private Role role;
}
