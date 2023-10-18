package ru.skypro.homework.service.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.dto.usersDTO.Role;
import javax.persistence.*;



@Data
@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    ImageEntity image;
    @Enumerated(EnumType.STRING)
    Role role;
}