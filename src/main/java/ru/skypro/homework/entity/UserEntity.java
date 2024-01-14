package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(min = 8, max = 16)
    private String password;

    @Column(name = "user_name", nullable = false)
    @Size(min = 4, max = 32)
    private String userName;

    @Column(name = "first_name")
    @Size(min = 2, max = 16)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 16)
    private String lastName;

    private String email;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Ad> ads;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Comment> comments;

}
