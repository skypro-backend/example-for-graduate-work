package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    int id;
    String image;
    String email;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "phone")
    String phone;
    @Column(name = "role")
    Role role;
    @Column(name = "password")
    String password;
    //Collection<Ad> ads;
}
