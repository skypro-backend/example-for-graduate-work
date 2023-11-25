package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer phone;
    private Role role;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
