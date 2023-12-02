package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private Role role;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public String getEmail() {return null;
    }
}
