package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Integer pk; // id пользователя

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?(\\d{3})\\)?\\s?(\\d{3}-?\\d{2}-?\\d{2})")
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Pattern(regexp = "USER|ADMIN")
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author")
    private List<AdEntity> ads;


    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;

}