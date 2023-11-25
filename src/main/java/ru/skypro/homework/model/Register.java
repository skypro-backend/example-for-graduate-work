package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity(name = "register")
@RequiredArgsConstructor
@NoArgsConstructor
public class Register {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
}
