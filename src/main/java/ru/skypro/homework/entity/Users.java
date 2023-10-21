package ru.skypro.homework.entity;


import lombok.*;
import ru.skypro.homework.dto.Role;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String image;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    @Enumerated
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<Comment> commentList;

}