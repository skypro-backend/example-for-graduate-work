package ru.skypro.homework.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String imagePath;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pk_id")
    private List<Ad> ad;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User(Integer id, String username, String password, String firstName, String lastName, String phone, String imagePath, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.imagePath = imagePath;
        this.role = role;
    }
}
