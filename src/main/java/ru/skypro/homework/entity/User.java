package ru.skypro.homework.entity;


import lombok.*;
import lombok.experimental.Accessors;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
@Accessors(chain = true)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String image;



}
