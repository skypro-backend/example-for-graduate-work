package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.skypro.homework.dto.RoleDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDTO role;
    private String image;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private Collection<Ad> ads;

}
