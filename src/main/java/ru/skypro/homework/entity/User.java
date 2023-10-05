package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.dto.usersDTO.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String username;
    private String password;
    String firstName;
    String lastName;
    String phone;
    String email;
    String image;
}
